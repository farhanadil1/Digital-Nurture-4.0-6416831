package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);

        testAll();
    }

    private static void testAll() {
        LOGGER.info("Start");

        LOGGER.debug("Find by code IN: {}", countryService.findCountryByCode("IN"));

        Country newCountry = new Country();
        newCountry.setCode("ZZ");
        newCountry.setName("Zootopia");
        countryService.addCountry(newCountry);
        LOGGER.debug("Added: {}", newCountry);

        countryService.updateCountry("ZZ", "Zootropolis");
        LOGGER.debug("Updated: {}", countryService.findCountryByCode("ZZ"));

        List<Country> searchResults = countryService.findCountriesByPartialName("land");
        LOGGER.debug("Search 'land': {}", searchResults);

        countryService.deleteCountry("ZZ");
        LOGGER.debug("Deleted ZZ. Result: {}", countryService.findCountryByCode("ZZ"));

        LOGGER.info("End");
    }
}
