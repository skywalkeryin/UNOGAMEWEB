package model;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;

        


public class UNOgame {
    private String gameName;
    private String gameID;
    private ArrayList<Players> players ;
    private String  gameStatus;   //={"waiting","started","ended"};
    private Deck deck ;
    private LinkedList<UNOCard> discardPile;
    public UNOgame(){
       deck=new Deck();
       players =new ArrayList<Players>();
       discardPile=new  LinkedList<UNOCard>();
    }
    public UNOgame(String name,String id,ArrayList<Players> player,String status,Deck deck,LinkedList<UNOCard> pile){
        this.gameName=name;
        this.gameID=id;
        this.players=player;
        this.gameStatus=status;
        this.deck=deck;
        this.discardPile=pile;
       
        
    }

    public Deck getDeck() {
        return deck;
    }

    public LinkedList<UNOCard> getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Players> getPlayers() {
        return players;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameID() {
        return gameID;
    }

    public String getGameStatus() {
        return gameStatus;
    }
    
  
    

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setPlayers(ArrayList<Players> players) {
        this.players = players;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setDiscardPile(LinkedList<UNOCard> discardPile) {
        this.discardPile = discardPile;
    }
    
    
    public void  createNewgame(){
        deck.createDeck();
       // LinkedList<UNOCard>card=
       UNOCard c=deck.takeCard();
         discardPile.addFirst(c);
        
        
    }
    public void addPlayer(Players p){
      //  this.players=new ArrayList<Players>();
        players.add(p);
        
    }
    public UNOCard takecardfromdeck(){
        Deck deck=new Deck();
        UNOCard c=deck.takeCard();
        return c;
    }

    public void discard(UNOCard card,Players p ){
       // Players p=new Players();
        LinkedList<UNOCard> a= p.getHands();
        discardPile.addFirst(card);
        a.remove(card);
        
    }

    
    public String changeStatus(String status){
        gameStatus=status;
        return status;
    }
    public void deal( Players p){
       for(int i=0;i<7;i++){
         p.addCard(deck);
       
    }
      
   }
     public JsonObject toJson() {
	   JsonObject game=Json.createObjectBuilder()
				.add("gameID", gameID)
				.add("gameName", gameName)
				.add("gameStatus",gameStatus)
                                .build();
           return game;
				
	}
        
}
    

