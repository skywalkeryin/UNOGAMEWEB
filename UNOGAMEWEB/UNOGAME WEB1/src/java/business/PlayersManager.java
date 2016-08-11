
package business;

import java.util.ArrayList;
import java.util.UUID;
import javax.ejb.Stateless;
import model.Deck;
import model.Players;


@Stateless
public class PlayersManager {
    private  ArrayList<Players> globalplayers=new  ArrayList<Players>();
    Deck deck=new Deck();
    public ArrayList<Players> createplayers(String name){
      //  Deck deck=new Deck();
        String pid = UUID.randomUUID().toString().substring(0, 8);
        Players player=new Players();
        player.setPid(pid);
        player.setName(name);
//        for(int i=0;i<7;i++){
//        player.addCard(deck);
//        };
//        
        globalplayers.add(player);
        return globalplayers;
        
    }
    
   public ArrayList<Players> getplayers(){
        return globalplayers;
   } 
        
}
