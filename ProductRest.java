/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.rest;

import com.quickrest.facade.ProductFacade;
import com.quickrest.entities.Product;
import com.quickrest.entities.Supplier;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
@Path("product")
public class ProductRest {

    @Inject
    ProductFacade productFacade;

    @Resource
    ManagedExecutorService mes;

    private List<Product> productList = new ArrayList<>();
    private List<Supplier> supplierList = new ArrayList<>();

    @Path("findAll")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Product> findAll(@Suspended AsyncResponse asyncResponse) {

        asyncResponse.setTimeout(2, TimeUnit.MINUTES);
        asyncResponse.setTimeoutHandler(th -> {
            th.resume(Response.status(Response.Status.REQUEST_TIMEOUT).build());
        });

        mes.submit(() -> {
            productList = productFacade.findAll();
            asyncResponse.resume(Response.ok(productList).build());
        });

        return productList;
    }

    @Path("findAllSuppliers")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Supplier> findAllSuppliers(@Suspended AsyncResponse asyncResponse) {

        asyncResponse.setTimeout(2, TimeUnit.MINUTES);
        asyncResponse.setTimeoutHandler(th -> {
            th.resume(Response.status(Response.Status.REQUEST_TIMEOUT).build());
        });

        mes.submit(() -> {
            supplierList = productFacade.findSuppliers();
            asyncResponse.resume(Response.ok(supplierList).build());
        });

        return supplierList;
    }

    @Path("findBySupplier/{identity}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    public List<Product> find(@PathParam("identity") String identity) {
        return productFacade.findProductBySupplier(identity);
    }

    @Path("findByCode/{code}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @GET
    public List<Product> findByCode(@PathParam("code") String code) {
        return productFacade.findProductByCode(code);
    }

    @Path("delete/{id}")
    @DELETE
    public void remove(@PathParam("id") BigDecimal id) {
        productFacade.deleteProduct(id);
    }

    @Path("create")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Product entity) {
        productFacade.create(entity);
        return Response.ok(entity).build();
    }

}
