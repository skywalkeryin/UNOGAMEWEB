package model;



import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;


public class Players {
    private String pid;
    private String name;
    LinkedList<UNOCard>hands;
    Deck deck;
    
    public Players(){
       hands =new LinkedList<UNOCard>();
       deck =new Deck();
    }
    public Players( String pid,String name,LinkedList<UNOCard> hands,Deck deck){
        this.pid=pid;
        this.name=name;
        this.hands=hands;
        this.deck=deck;
    }
    public String getName() {
        return name;
    }

    public String getPid() {
        return pid;
    }

    public Deck getDeck() {
        return deck;
    }
    public Players(String name){
        this.name=name;
    }
    public LinkedList<UNOCard> getHands(){
        return hands;
    } 
    public void setName(String name){
        this.name=name;
    }
      public void setPid(String pid) {
        this.pid = pid;
    }

    public void setHands(LinkedList<UNOCard> hands) {
        this.hands = hands;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
     
    public LinkedList<UNOCard> addCard(Deck deck){
        hands.add(deck.takeCard());
        return hands;
        
    }


    public LinkedList<UNOCard> removeCard(){
        Random rand=new Random();
        int n=rand.nextInt(hands.size());
        hands.remove(n);
        return hands;
    }
    
     public JsonObject toJson() {
	   JsonObject player =Json.createObjectBuilder()
                                .add("pid", pid)
				.add("name", name)
				//.add("gameName", gameName)
				//.add("gameStatus",gameStatus)
                                .build();
           return player;
				
	}
    
}
