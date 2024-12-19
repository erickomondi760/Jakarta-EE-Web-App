/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.facade;

import com.quickrest.entities.Product;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author USER
 */
@Transactional
@Stateless
public class ProductFacade{

    @Inject
    EntityManager entityManager;

    public void create(Product entity) {
       entityManager.persist(entity);
    }

    public List<Product> findAll() {
        return entityManager.createQuery("select o from Product o").getResultList();
    }

    
}
