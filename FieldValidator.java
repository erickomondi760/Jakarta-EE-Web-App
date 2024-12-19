/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.resources;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
@Provider
public class FieldValidator implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException e) {
        final Map<String,String> violation = new HashMap<>();
        for(ConstraintViolation cv:e.getConstraintViolations()){
            violation.put(cv.getPropertyPath().toString().split("\\.")[2]
                    , cv.getMessage());
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(violation)
                .build();
    }
    
}