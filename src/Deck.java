import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck
{
   ArrayList<Card> deck = new ArrayList<>();
   
   public Deck()
   {
      for (Card.Color color : Card.Color.values()) 
      {
            for (Card.Fill fill : Card.Fill.values()) 
            {
                for (Card.Shape shape : Card.Shape.values()) 
                {
                    for (Card.Number number : Card.Number.values()) 
                    {
                        deck.add(new Card(number, color, shape, fill));
                    } 
                }
            }
      }   
   }
   /** shuffle() rearranges the deck of cards at random
   */
   public void shuffle()
   {
      Collections.shuffle(deck, new Random( ));
   }
   
   /** isEmpty returns if the deck of cards is empty
       @return true or false depending if the deck is empty
   */
   public boolean isEmpty()
   {
      if (deck.size() == 0)
         return true;
      else
         return false;
   }
   /** getTopCard() finds the top card in the deck
         @return the top card in the deck */
   public Card getTopCard()
   {
      return deck.remove(0);
   }
   /** cardsRemaining) counts how many cards are left in the deck
       @return the number of cards in the deck */
   public int cardsRemaining()
   {
      return deck.size();
   }
   
    /** toString() to properly format variables
       @return formatted output
   */
   @Override
   public String toString()
   {
      String s = "";
      for (Card c : deck)
      {
         s += c.toString();
         s += "   ";
      }
   return s;
   }
}
