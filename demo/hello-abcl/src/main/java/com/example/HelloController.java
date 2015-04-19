package com.example;

import java.time.LocalDateTime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
@RequestScoped
@Controller
public class HelloController {

    @Inject
    Models models;
    
    @GET
    @Path("abcl")
    public String helloAbcl() {
        models.put("title", "Sample of ABCL");
        models.put("body", "Hello from ABCL @ " + LocalDateTime.now());
        return "template.lisp";
    }
}
