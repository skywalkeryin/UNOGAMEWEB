
package REST;

import business.GameManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import model.UNOgame;

@RequestScoped
@Path("/game")
public class GameResource {
    
      @EJB private GameManager GameMgr;
      @GET
      //@Path("{gid}/games")
      @Produces(MediaType.APPLICATION_JSON)
      public Response getgame(@DefaultValue("") @QueryParam("display") String display){
         //System.out.println(">>> query parameter: " + display);
         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
         Set<String> k= GameMgr.getallgames().keySet();
         k.stream().forEach((key) -> {
              arrBuilder.add(GameMgr.getallgames().get(key).toJson());
          });
         
	return (Response.ok(arrBuilder.build()).build());
     }
      
    
    @GET
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getgame(){
          JsonObject game= GameMgr.getlastgame().toJson();
          return (Response.ok(game).build());
    }
   
    @GET
    @Path("/startgame")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startgame(){
       
      JsonObject card= GameMgr.getlastgame().getDiscardPile().getFirst().toJson();
          return (Response.ok(card).build());
    }
      
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public void creategame(MultivaluedMap<String,String> form){
         String gname=form.getFirst("title");
         GameMgr.creategame(gname);
    }
    
    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startgame(MultivaluedMap<String,String> form){
        GameMgr.getlastgame().createNewgame();
        try{
         URI location =new java.net.URI("http://localhost:8080/UNOGAME_WEB1/StartGame.html");
         return Response.seeOther(location).build();
        }
        catch(URISyntaxException ex){
            return Response.status(404).entity("fail to find").build();
        }
    }
      
}    
    

      


    
