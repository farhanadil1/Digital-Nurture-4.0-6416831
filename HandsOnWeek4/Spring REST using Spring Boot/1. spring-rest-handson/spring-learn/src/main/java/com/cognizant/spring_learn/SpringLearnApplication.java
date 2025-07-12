package com.cognizant.spring_learn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringLearnApplication.class, args);
        //Hands-on 2 : Load SimpleDateFormat from Spring Configuration XML 
        //displayDate();
        
        // Hands-on 4 : Load Country from Spring Configuration XML 
        //displayCountry(); 
        
        // Hands-on 6 : Load list of countries from Spring Configuration XML 
        displayCountries();
    }
    
    // Hands-on 3 : Incorporate Logging
    public static void displayDate() throws Exception {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

        Date parsedDate = format.parse("31/12/2018");
        LOGGER.debug("Parsed Date: {}", parsedDate);

        LOGGER.info("END");
    }
    public static void displayCountry() {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        Country country = context.getBean("country", Country.class);
        // Hands-on 5 :Demonstration of Singleton Scope and Prototype Scope 
        Country anotherCountry = context.getBean("country", Country.class);

        LOGGER.debug("Country : {}", country.toString());
        LOGGER.debug("Another Country : {}", anotherCountry.toString());

        LOGGER.info("END");
    }
    public static void displayCountries() {
        LOGGER.info("START");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = context.getBean("countryList", ArrayList.class);

        LOGGER.debug("Country List : {}", countryList);

        LOGGER.info("END");
    }



}
