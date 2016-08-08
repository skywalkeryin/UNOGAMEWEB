/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import model.UNOgame;
import javax.ws.rs.Produces;
import static javax.ws.rs.client.Entity.form;
import static javax.ws.rs.client.Entity.form;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author Administrator
 */
@RequestScoped
@Path("resource")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class GameResource {
    @POST
    @Path("creategame")
    @Produces("application/json")
    public  String getgame(){
    UNOgame a =new UNOgame();
  //  a.=form.getFrist("title");
    
    
}
    
    
}
