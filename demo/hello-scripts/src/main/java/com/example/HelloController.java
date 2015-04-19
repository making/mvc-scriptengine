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
    @Path("react")
    public String helloReact() {
        models.put("title", "Sample of React.js");
        models.put("body", "Hello from React.js @ " + LocalDateTime.now());
        return "template.js";
    }

    @GET
    @Path("reactJsx")
    public String helloReactJsx() {
        models.put("title", "Sample of React.js");
        models.put("body", "Hello from React.js (JSX) @ " + LocalDateTime.now());
        return "template.jsx";
    }
    
    @GET
    @Path("handlebars")
    public String helloHandlebars() {
        models.put("title", "Sample of Handlebars");
        models.put("body", "Hello from Handlebars @ " + LocalDateTime.now());
        return "template.hbs";
    }   
    
    @GET
    @Path("mustache")
    public String helloMustache() {
        models.put("title", "Sample of mustache.js");
        models.put("body", "Hello from mustache.js @ " + LocalDateTime.now());
        return "template.mst";
    }  
    
    @GET
    @Path("jython")
    public String helloJython() {
        models.put("title", "Sample of Jython");
        models.put("body", "Hello from Jython @ " + LocalDateTime.now());
        return "template.py.html";
    }   
    
    @GET
    @Path("erb")
    public String helloErb() {
        models.put("title", "Sample of ERB");
        models.put("body", "Hello from ERB @ " + LocalDateTime.now());
        return "template.erb";
    }
}
