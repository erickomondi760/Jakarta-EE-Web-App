/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.facade;

import com.quickrest.entities.Product;
import com.quickrest.entities.Supplier;
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
    
    public List<Product> findProductBySupplier(String identity){
        return entityManager.createNamedQuery(Product.FIND_PRODUCT_BY_SUPPLIER,Product.class)
                .setParameter("identity", identity).getResultList();
    }
    
    public List<Product> findProductByCode(String code){
        return entityManager.createNamedQuery(Product.FIND_PRODUCT_BY_CODE,Product.class)
                .setParameter("code", code).getResultList();
    }
    
    public void deleteProduct(BigDecimal id){
        entityManager.createNamedQuery(Product.DELETE_PRODUCT).setParameter("id", id);
    }
    
    public List<Supplier> findSuppliers(){
        return entityManager.createNamedQuery(Product.FIND_SUPPLIERS,Supplier.class).getResultList();
    }

   

    

    
}
