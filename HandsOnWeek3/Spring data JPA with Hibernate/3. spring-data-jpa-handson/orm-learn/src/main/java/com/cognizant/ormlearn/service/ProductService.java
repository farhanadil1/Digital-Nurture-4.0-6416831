package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Product;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> search(String os, Double minRamSize, String cpu) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (os != null && !os.isEmpty()) {
            predicates.add(cb.equal(product.get("os"), os));
        }

        if (minRamSize != null) {
            predicates.add(cb.ge(product.get("ramSize"), minRamSize));
        }

        if (cpu != null && !cpu.isEmpty()) {
            predicates.add(cb.equal(product.get("cpu"), cpu));
        }

        cq.select(product).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
