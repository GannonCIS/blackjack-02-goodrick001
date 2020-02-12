/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack_02;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
        
    private Deck myDeck = new Deck();
    
    private Player[] myPlayers;
    
    private Hand dealerHand = new Hand();
    
    private Hand myHand = new Hand();
    
    Scanner scan = new Scanner(System.in);
    
    public Dealer (int numOfPlayers){
         initPlayers(numOfPlayers);
    }
    
    public Dealer(){
        System.out.println(" How many want to play? ");
        int num = scan.nextInt();
        initPlayers(num);
    }
    public void playGame(){
        this.dealOutOpeningHand();
        this.playOutPlayerHands();
        this.playOutDealerHand();
        this.declareWinner();
    }
    
    public void dealOutOpeningHand(){
        for(int i = 0; i < 2; i++){
            for(Player currPlayer : myPlayers){
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
        }
        
    }
    
    public void playOutPlayerHands(){
       for(Player currPlayer : myPlayers){
           System.out.println(currPlayer.getName() + "'s Hand");
           currPlayer.getMyHand().printHand();
           while(currPlayer.getMyHand().getNumOfCards() < 5 &&
                   currPlayer.getMyHand().getScore() < 21){
               System.out.println(currPlayer.getName()
                      + " Wanna hit? (y/n)");
               char opt = scan.next().toLowerCase().charAt(0);
               System.out.println("\n");
               if(opt == 'y'){
                   currPlayer.getMyHand().addCard(myDeck.dealCard());
                   
               }else{
                   break;
               }
               currPlayer.getMyHand().printHand();
           }
       }
        
    }
    
    public void playOutDealerHand(){
        while(dealerHand.getScore() < 16 && dealerHand.getNumOfCards() < 5){
            dealerHand.addCard(myDeck.dealCard());
        }
        System.out.println(" Dealer's Hnad ");
        dealerHand.printHand();
    }
    
    public void declareWinner(){
        
        System.out.println("Dealers's Hand: ");
        dealerHand.printHand();
        
        for(int i=0; i< myPlayers.length; i++){
            Player currPlayer = myPlayers[i];
            System.out.println(currPlayer.getName() +"'s Hand");
            currPlayer.getMyHand().printHand();
            if(dealerHand.getScore() > 21 ||
                    currPlayer.getMyHand().getScore() > 21){
                if(currPlayer.getMyHand().getScore() > 21){
                    System.out.println(currPlayer.getName() + 
                            " You Busted....Loser");
                }else{
                    System.out.println(currPlayer.getName() + 
                            " The dealer busted... you got lucky");
                }
            }else if(dealerHand.getScore() == 21 || 
                    dealerHand.getNumOfCards() > 4){
                System.out.println(currPlayer.getName() + 
                            " The dealer has bested you... not your day");
            }else if(currPlayer.getMyHand().getNumOfCards() > 4){
                System.out.println(currPlayer.getName() + 
                            " You win this time but don't get cocky");
            }else if(currPlayer.getMyHand().getScore() > dealerHand.getScore()){
                System.out.println(currPlayer.getName() + 
                            " You win now but the dealer is coming for you");
            }else{
                System.out.println(currPlayer.getName() + 
                        " you lost because you're bad.. you should quit");
            }
        }
        
    }
    private void initPlayers(int numOfPlayers){
        myPlayers = new Player[numOfPlayers];
        for(int i = 0; i <myPlayers.length; i++){
            System.out.println("Player " + i+1 + " whats your name ");
            String name = scan.next();
            if(name.equals("")){
                myPlayers [i] = new Player(i+1);
            }else{
              myPlayers[i] = new Player(name); 
            }
        }
    }
}
    
