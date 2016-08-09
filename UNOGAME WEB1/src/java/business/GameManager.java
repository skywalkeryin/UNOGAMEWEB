
package business;

import java.util.ArrayList;
import java.util.Collection;
import model.UNOgame;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class GameManager {
    
   // @PersistenceContext private EntityManager em;
    private ArrayList<UNOgame> globalgame=new ArrayList<UNOgame>();
       
      
    
    public ArrayList<UNOgame> creategame( String name){
          String gid = UUID.randomUUID().toString().substring(0, 8);
          UNOgame game=new UNOgame();
          game.setGameID(gid);
          game.setGameName(name);
          game.setGameStatus("wating");
          //em.persist(game);
          globalgame.add(game);
          return globalgame;
        // return game;
    }
    public ArrayList<UNOgame> getallgames(){
         return globalgame;
        
    }
          
       
        
        
        
   
    
}
