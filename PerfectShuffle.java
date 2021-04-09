//
//  Name:       Kwan, Wesley
//  Homework:   2
//  Due:        03/06/20
//  Course:     cs-2400-02
//
//  Description:
//              Shuffles an ordered deck of cards until it reverts back
//              to its original state.
//
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class PerfectShuffle
{
    public static void main(String[] args)
    {
        System.out.println("Card Shuffle by W. Kwan\n"); 
        int size = 52;
        if (args.length > 0)
        {
            size = Integer.parseInt(args[0]);
        }
        System.out.println(size + " Cards\n");
        
        int[] deck = new int[size];
        int[] ogDeck = new int[size];
        for (int i = 0; i < size; i++)
        {
            deck[i] = i + 1;
            ogDeck[i] = i + 1;
        }
       
        System.out.println("Original:");
        for (int card: deck)
            System.out.println(cardName(card));
        System.out.println();
        
        int roundNum = 0;
        do
        {
            inShuffle(deck);
            System.out.println("Round " + (++roundNum) + ":");
            for (int card: deck)
                System.out.println(cardName(card));
            System.out.println();
        } while(!Arrays.equals(deck, ogDeck));
        
        System.out.println("Original order in " + roundNum + " in-shuffle.");
    }
    public static void inShuffle(int[] deck)
    {
        Deque<Integer> topDeck = new ArrayDeque<>();
        Deque<Integer> botDeck = new ArrayDeque<>();
        
        for (int i = 0; i < deck.length; i++)
        {
            if (i < deck.length / 2)
                topDeck.add(deck[i]);
            else
                botDeck.add(deck[i]);
        }
        
        // botDeck.length >= topDeck.length
        int index = deck.length - 1;
        while(!botDeck.isEmpty())
        {
            if (!topDeck.isEmpty())
                deck[index--] = topDeck.removeLast();
            deck[index--] = botDeck.removeLast();
        }
    }
    public static String cardName(int value) 
    { 
        final String[] suits = {"Spade", "Diamond", "Heart", "Club" };
        final String[] cards = { "Jack", "Queen", "King" };
        
        --value;
        String sValue;
        int iValue = value % 13;
        switch (iValue)
        {
            case 0:
                sValue = "Ace";
                break;
            case 10: case 11: case 12:
                sValue = cards[iValue - 10];
                break;
            default:
                sValue = Integer.toString(iValue + 1);
                break;
        }
        
        return sValue + " of " + suits[value / 13];
    }
}