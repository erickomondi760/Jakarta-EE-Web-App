/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.rest;

import com.quickrest.facade.ProductFacade;
import com.quickrest.entities.Product;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    @Context
    URIInfo uriInfo;

    private List<Product> productList = new ArrayList<>();

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


    @Path("create")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(@Valid Product entity) {
        productFacade.create(entity);
        URI uri = uriInfo.getAbsolutePathBuilder().path(entity.getId()).build();
        return Response.created(uri).ststus(Response.Status.CREATED).build();
    }

}
