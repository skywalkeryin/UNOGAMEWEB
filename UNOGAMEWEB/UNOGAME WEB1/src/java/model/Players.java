package model;



import java.util.LinkedList;
import java.util.Random;


public class Players {
    private String pid;
    private String name;
    LinkedList<UNOCard>hands=new LinkedList<UNOCard>();
    Deck deck=new Deck();
    
    public Players(){
        super();
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
    
        
    
}
