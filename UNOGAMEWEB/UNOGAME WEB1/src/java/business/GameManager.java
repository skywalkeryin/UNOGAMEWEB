
package business;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import model.UNOgame;
import javax.ejb.Stateless;
import java.util.LinkedHashMap;
import java.util.UUID;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import model.Players;






@Stateless
public class GameManager {
    
     
      private LinkedHashMap<String,UNOgame> globalgame=new LinkedHashMap<String,UNOgame>();
      private ArrayList<UNOgame> globalgamelist=new ArrayList<UNOgame>();
      private  ArrayList<Players> globalplayers=new  ArrayList<Players>();
      
    
    public  LinkedHashMap<String,UNOgame> creategame( String name){
          String gid = UUID.randomUUID().toString().substring(0, 8);
          UNOgame game=new UNOgame();
          game.setGameID(gid);
          game.setGameName(name);
          game.setGameStatus("wating");
       
        globalgame.put(gid, game);
        return globalgame;
    }

    public  LinkedHashMap<String,UNOgame> getallgames(){

          return globalgame;
        
    }
    public UNOgame getlastgame(){
         String lKeyLast = null;
    if (!globalgame.isEmpty()){
      
      for(String key : globalgame.keySet()){
        lKeyLast = key;
      }
     
      }// 用于开始游戏，找到最后一个创建的游戏。
     return ( globalgame.get(lKeyLast));
    }
       

    public  void addplayers(String gid,ArrayList<Players> players){
        UNOgame a=new UNOgame();
        a=globalgame.get(gid);
        a.setPlayers(players);
        globalgame.put(gid, a );
     
   }
    public ArrayList<Players>  getplayerlist(String gid){
         return  (globalgame.get(gid).getPlayers());
        }
          
       
        
        
        
   
    
}
