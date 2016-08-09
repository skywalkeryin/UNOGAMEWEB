
package REST;

import business.GameManager;
import java.util.ArrayList;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
          GameMgr.getallgames()
				.stream()
				
				.map((UNOgame g) -> { 
					return (g.toJson()); })
				.forEach(j -> { 
					arrBuilder.add(j);
				});
		return (Response.ok(
				arrBuilder.build()
		).build());
			
 
     }
   
      
      
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creategame(MultivaluedMap<String,String> form){
         String gname=form.getFirst("title");
         // UNOgame game= new UNOgame();
         ArrayList<UNOgame> gamelist= GameMgr.creategame(gname);
        // JsonObjectBuilder j=Json.createObjectBuilder();
        // gamelist.get(0).toJson();
         
         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
         gamelist.stream().map((UNOgame g) -> { 
					return (g.toJson()); })
				.forEach(j -> { 
					arrBuilder.add(j);
				});
        
        return (Response.ok(arrBuilder.build()).build());
     // return (Response.ok( gamelist.get(0).toJson()).build());
   
       
    }
    
    
}
      
      
    

      


    
