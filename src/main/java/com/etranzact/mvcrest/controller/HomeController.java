package com.etranzact.mvcrest.controller;

import com.etranzact.mvcrest.Mail;
import com.etranzact.mvcrest.MailTask;
import com.etranzact.mvcrest.Model.User;
import com.etranzact.mvcrest.ThreadPool;
import org.glassfish.jersey.server.mvc.Viewable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HomeController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable index() {
        Map<String, String> model = new HashMap<>();
        model.put("hello", "Hello");
        model.put("world", "World");
        return new Viewable("/index", model);
    }

    @GET
    @Path("/manage")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getManagePage() {
        Map<String, String> model = new HashMap<>();
        model.put("hello", "manage");
        model.put("world", " Page");
        return new Viewable("/manage", model);
    }

    @GET
    @Path("/error")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getErrorPage() {
        Map<String, String> model = new HashMap<>();
        model.put("hello", "Error");
        model.put("world", " Page");
        return new Viewable("/templates/error", model);
    }

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText(){
        return Response.status(200).entity("Well done bassey").build();
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(){
        return Response.status(200).entity(getUser()).build();
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response getXml(){
        return Response.status(200).entity(getUser()).build();
    }

    @GET
    @Path("/mail/{email_address}")
    @Produces(MediaType.APPLICATION_XML)
    public Response sendMail(@PathParam("email_address") String emailAddress){
        Mail mail = new Mail();
        mail.setTo(emailAddress);
        MailTask mailTask = new MailTask(mail);

        int queueSize = 3;
        int numberOfThreads = 4;
        ThreadPool threadPool = new ThreadPool(queueSize, numberOfThreads);
        threadPool.submitTask(mailTask);

        return Response.status(200).entity(getUser()).build();
    }

    private User getUser(){
        User user = new User();
        user.setEmail("asukwo.enenim@etranzact.com");
        user.setFirstName("Asukwo");
        user.setLastName("Enenim");
        user.setId(1L);
        return user;
    }
}
