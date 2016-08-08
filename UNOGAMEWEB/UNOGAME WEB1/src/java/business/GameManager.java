
package business;

import model.UNOgame;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class GameManager {
    
    @PersistenceContext private EntityManager em;
    private Map<String ,JsonObject> globalgame=new HashMap<>();
    
    public Map<String ,JsonObject> creategame( String name){
          String gid = UUID.randomUUID().toString().substring(0, 8);
          UNOgame game=new UNOgame();
          game.setGameID(gid);
          game.setGameName(name);
          game.setGameStatus("wating");
          //em.persist(game);
          globalgame.put(gid, game.toJson());
          return globalgame;
    }
          
       
        
        
        
   
    
}
