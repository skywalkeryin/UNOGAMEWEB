
package REST;

import business.GameManager;
import business.PlayersManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
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
import javax.ws.rs.PathParam;
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
      private static String pname=null;

      @GET
      @Path("/playeringame")
      @Produces(MediaType.APPLICATION_JSON)
      public Response getgame(@DefaultValue("") @QueryParam("display") String display){
       
       JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

         
        JsonObject a=GameMgr.getallgames().get(gid).toJson();
	return (Response.ok(a).build()); 
        
			
}
      @GET
      @Path("/playerid")
      @Produces(MediaType.APPLICATION_JSON)
      public Response getplayer(@DefaultValue("") @QueryParam("display") String display){
       
       JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

          int a=0;
         for(int i=0;i<GameMgr.getallgames().get(gid).getPlayers().size();i++){
            if( GameMgr.getallgames().get(gid).getPlayers().get(i).getName() ==pname){
             a=i;
             break;
            }
                
            
         }
 
       
	return (Response.ok(GameMgr.getallgames().get(gid).getPlayers().get(a).toJson()).build()); 
        
			
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
      
      
      
    @GET
    @Path("/startgame")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getgame(){
          JsonObject game= GameMgr.getallgames().get(gid).toJson();
          return (Response.ok(game).build());
    }
   
//      @GET
//      @Path("{pid}")
//      @Produces(MediaType.APPLICATION_JSON)    
//      public Response getownhands (@PathParam("pid") String pid){
//         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
//         Players p=new Players();
//         int a=0;
//         for(int i=0;i<10;i++){
//            if( GameMgr.getallgames().get(gid).getPlayers().get(i).getPid()==pid){
//                a=i;
//                
//             break;
//            }
//            
//          }
//         // GameMgr.getallgames().get(gid).getPlayers().get(a);
//            p=GameMgr.getallgames().get(gid).getPlayers().get(a);
//           
//           
//                  p.getHands().stream().map(h -> {
//						return (h.toJson());
//					}).forEach(j -> {
//						arrBuilder.add(j);
//					});
//        
//         
//          //找到一个玩家
//       
//          return (Response.ok(arrBuilder.build()).build());
//     }

            
    @POST
    @Path("/join")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creategame(MultivaluedMap<String,String> form){
           gid=form.getFirst("gid");
           pname=form.getFirst("pname");
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
    
     private  static int a=-1;
      @GET
      @Path("/playerhands/{playerid}")
      @Produces(MediaType.APPLICATION_JSON)    
      public Response getownhands (@PathParam("playerid") String pname){
         JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
         a++;
         Players p=GameMgr.getallgames().get(gid).getPlayers().get(a); //找到一个玩家
         p.getHands().stream().map(h -> {
						return (h.toJson());
					}).forEach(j -> {
						arrBuilder.add(j);
					});
          return (Response.ok(arrBuilder.build()).build());
     }
}
