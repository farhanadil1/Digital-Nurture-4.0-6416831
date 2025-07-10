package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.DepartmentRepository;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import com.cognizant.ormlearn.repository.SkillRepository;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static CountryService countryService;
    private static StockRepository stockRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        // Initialize beans
        countryService = context.getBean(CountryService.class);
        stockRepository = context.getBean(StockRepository.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        // Run tests
        // Hands-on 1
        //searchCountryByPartialNameTest();
        //searchCountryByPartialNameSortedTest();
        //searchCountryByAlphabetTest();

        // Hands-on 2
        //testStockQueries();

        // Hands-on 3
        //EmployeeTester tester = context.getBean(EmployeeTester.class);
        //tester.testEmployeeFetch();
        
        // Hands-on 4
        //testGetEmployee();
        //testAddEmployee();
        //testUpdateEmployee();
        
        // Hands-on 5
        //testGetDepartment();
        
        // Hands-on 6
        testAddSkillToEmployee();
        
    }

    private static void searchCountryByPartialNameTest() {
        LOGGER.info("Start - Partial Search");

        List<Country> countries = countryService.findByNameContaining("ou");
        countries.forEach(country -> LOGGER.debug("Country: {}", country));

        LOGGER.info("End - Partial Search");
    }

    private static void searchCountryByPartialNameSortedTest() {
        LOGGER.info("Start - Sorted Search");

        List<Country> countries = countryService.findByNameContainingOrderByNameAsc("ou");
        countries.forEach(country -> LOGGER.debug("Country (Sorted): {}", country));

        LOGGER.info("End - Sorted Search");
    }

    private static void searchCountryByAlphabetTest() {
        LOGGER.info("Start - Alphabet Search");

        List<Country> countries = countryService.searchByAlphabet("Z");
        countries.forEach(country -> LOGGER.debug("Country (Starts with Z): {}", country));

        LOGGER.info("End - Alphabet Search");
    }

    private static void testStockQueries() {
        LOGGER.info("Start - Stock Queries");

        List<Stock> fbSeptStocks = stockRepository.findByCodeAndDateBetween(
                "FB", LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        fbSeptStocks.forEach(stock -> LOGGER.info("FB Sept: {}", stock));

        List<Stock> googleHighStocks = stockRepository.findByCodeAndCloseGreaterThan(
                "GOOGL", new BigDecimal("1250"));
        googleHighStocks.forEach(stock -> LOGGER.info("GOOGL >1250: {}", stock));

        List<Stock> topVolumeStocks = stockRepository.findTop3ByOrderByVolumeDesc();
        topVolumeStocks.forEach(stock -> LOGGER.info("Top Volume: {}", stock));

        List<Stock> lowestNetflix = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        lowestNetflix.forEach(stock -> LOGGER.info("Lowest NFLX: {}", stock));

        LOGGER.info("End - Stock Queries");
    }

    //  Inner bean to handle @Transactional lazy fetch
    @Component
    public static class EmployeeTester {

        @Autowired
        private EmployeeRepository employeeRepository;

        @Transactional
        public void testEmployeeFetch() {
            LOGGER.info("Start - Employee Fetch with Department and Skills");

            Employee emp = employeeRepository.findById(1).get();
            LOGGER.debug("Employee: {}", emp);
            LOGGER.debug("Department: {}", emp.getDepartment());
            LOGGER.debug("Skills: {}", emp.getSkillList());

            LOGGER.info("End - Employee Fetch");
        }
    }
    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.info("End");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("Nahid Azad");
        employee.setSalary(BigDecimal.valueOf(60000.0));
        employee.setPermanent(true);
        employee.setDateOfBirth(Date.valueOf("1992-10-05"));

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Employee Saved: {}", employee);
        LOGGER.info("End");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Department newDept = departmentService.get(2);
        employee.setDepartment(newDept);

        employeeService.save(employee);
        LOGGER.debug("Employee Updated: {}", employee);
        LOGGER.info("End");
    }
    private static void testGetDepartment() {
        LOGGER.info("Start - testGetDepartment");

        Department department = departmentService.get(1); 
        LOGGER.debug("Department: {}", department);

        department.getEmployeeList().forEach(emp -> LOGGER.debug("Employee: {}", emp));

        LOGGER.info("End - testGetDepartment");
    }
    private static void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(2); 

        Set<Skill> skillList = employee.getSkillList();
        skillList.add(skill);

        employee.setSkillList(skillList);
        employeeService.save(employee);

        LOGGER.debug("Updated Employee with Skills: {}", employee);
        LOGGER.info("End");
    }

}

