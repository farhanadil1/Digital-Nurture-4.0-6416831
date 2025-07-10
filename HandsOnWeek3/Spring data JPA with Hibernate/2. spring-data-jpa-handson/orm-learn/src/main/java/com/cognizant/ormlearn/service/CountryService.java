package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    //hands on 1
    @Transactional
    public List<Country> findByNameContaining(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional
    public List<Country> findByNameContainingOrderByNameAsc(String keyword) {
        return countryRepository.findByNameContainingOrderByNameAsc(keyword);
    }

    @Transactional
    public List<Country> searchByAlphabet(String alphabet) {
        return countryRepository.findByNameStartingWith(alphabet);
    }

    



    
    
}
