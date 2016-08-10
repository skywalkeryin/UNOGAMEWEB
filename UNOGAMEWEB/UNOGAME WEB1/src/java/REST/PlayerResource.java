
package REST;

import business.GameManager;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("/player")
public class PlayerResource {
    
    // @EJB private GameManager GameMgr;
  //   @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public 
        
    @POST
    @Path("/join")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creategame(MultivaluedMap<String,String> form){
           String pname=form.getFirst("pname");
           
      }
}
