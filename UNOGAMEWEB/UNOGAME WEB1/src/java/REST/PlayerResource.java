
package REST;

import business.GameManager;
import business.PlayersManager;
import java.net.URI;
import java.net.URISyntaxException;
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
import model.Players;

@RequestScoped
@Path("/player")
public class PlayerResource {
    
     @EJB private GameManager GameMgr;
     @EJB private PlayersManager PlayersMgr;
     private  static String gid=null;

      @GET
      @Path("/playeringame")
      @Produces(MediaType.APPLICATION_JSON)
      public Response getgame(@DefaultValue("") @QueryParam("display") String display){
       
       JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

         
        JsonObject a=GameMgr.getallgames().get(gid).toJson();
	return (Response.ok(a).build()); 
        
			
}
      @GET
      @Path("/playerlistingame")
      @Produces(MediaType.APPLICATION_JSON)
      public Response getplaylist (@DefaultValue("") @QueryParam("display") String display){
         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

           GameMgr.getallgames().get(gid).getPlayers().
                              stream().map(p -> {
						return (p.toJson());
					}).forEach(j -> {
						arrBuilder.add(j);
					});

                      //  GameMgr.getallgames().get(gid).getPlayers().get(0).toJson();
      
	return (Response.ok(arrBuilder.build()).build());
			
}
//      @GET
//      @Path("/playerhands")
//      @Produces(MediaType.APPLICATION_JSON)    
//      public Response getownhands (){
//          JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
//          
//      }
      
    @POST
    @Path("/join")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creategame(MultivaluedMap<String,String> form){
           gid=form.getFirst("gid");
           String pname=form.getFirst("pname");
          // PlayersMgr.createplayers(pname);
           GameMgr.addplayers( gid,PlayersMgr.createplayers(pname));
        try{
         URI location =new java.net.URI("http://localhost:8080/UNOGAME_WEB1/WaitGame.html");
         return Response.seeOther(location).build();
        }
        catch(URISyntaxException ex){
            return Response.status(404).entity("fail to find").build();
        }
           
      }
}
