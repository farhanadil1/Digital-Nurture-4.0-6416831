package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static EmployeeService employeeService;
    private static AttemptService attemptService;
    private static ProductService productService;


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        employeeService = context.getBean(EmployeeService.class);
        attemptService = context.getBean(AttemptService.class);
        productService = context.getBean(ProductService.class);
        

        


        // Hands-on 2: Fetch all permanent employees with department and skills
        //testGetAllPermanentEmployees();
        
        // Hands-on 3 : Fetch quiz attempt details using HQL 
        //testAttemptFetch();
        
        // Hands-on 4 : Get average salary
        //testGetAverageSalary();
        
        // Hands-on 5 : Get all employees using Native Query 
        //testGetAllEmployeesNative();
        
        // Hands-on 6 : Criteria Query
        testProductSearch();

    }

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees: {}", employees);
        employees.forEach(e -> LOGGER.debug("Skills: {}", e.getSkillList()));
        LOGGER.info("End");
    }
    private static void testAttemptFetch() {
        LOGGER.info("Start");

        int userId = 1;
        int attemptId = 1;

        Attempt attempt = attemptService.getAttempt(userId, attemptId);
        LOGGER.debug("Attempt by user: {}", attempt.getUser().getName());
        LOGGER.debug("Attempt Date: {}", attempt.getDate());

        attempt.getAttemptQuestions().forEach(aq -> {
            System.out.println();
            System.out.println(aq.getQuestion().getText());

            aq.getQuestion().getOptions().forEach(opt -> {
                boolean isSelected = aq.getAttemptOptions().stream()
                    .anyMatch(ao -> ao.getOption().getId() == opt.getId() && ao.isSelected());

                System.out.printf("%d) %-12s %.1f\t%s\n",
                    opt.getId(), opt.getText(), opt.getScore(), isSelected);
            });
        });

        LOGGER.info("End");
    }
    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        
        double avgAll = employeeService.getAverageSalary();
        LOGGER.debug("Average Salary (All): {}", avgAll);

        int deptId = 1; 
        double avgDept = employeeService.getAverageSalaryByDepartment(deptId);
        LOGGER.debug("Average Salary (Dept {}): {}", deptId, avgDept);

        LOGGER.info("End");
    }
    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start - Native Query");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        employees.forEach(e -> LOGGER.debug("Employee: {}", e));
        LOGGER.info("End - Native Query");
    }
    private static void testProductSearch() {
        LOGGER.info("Start - Product Search");

        List<Product> result = productService.search("Windows", 8.0, "Intel i5");
        result.forEach(p -> LOGGER.debug("Product: {}", p));

        LOGGER.info("End - Product Search");
    }



}
