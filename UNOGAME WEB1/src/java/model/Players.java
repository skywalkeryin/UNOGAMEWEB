package model;



import java.util.LinkedList;
import java.util.Random;


public class Players {
    private String name;
    LinkedList<UNOCard>hands=new LinkedList<UNOCard>();
    Deck deck=new Deck();
    
    public Players(){
        super();
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
     
    public LinkedList<UNOCard> addCard(Deck deck){
        hands.add(deck.takeCard());
        return hands;
        
    }

    public String getName() {
        return name;
    }
    public LinkedList<UNOCard> removeCard(){
        Random rand=new Random();
        int n=rand.nextInt(hands.size());
        hands.remove(n);
        return hands;
    }
    
        
    
}
