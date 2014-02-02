package me.doeseckle.poker;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;
import me.doeseckle.poker.model.Card.Suit;

import com.google.common.collect.ImmutableSet;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final HandIdentifier handIdentifier = new HandIdentifier();

        try {
            final ImmutableSet<Card> hand = ImmutableSet.of(
                    new Card(Rank.FIVE, Suit.CLUBS),
                    new Card(Rank.THREE, Suit.CLUBS),
                    new Card(Rank.ACE, Suit.HEARTS),
                    new Card(Rank.FOUR, Suit.CLUBS),
                    new Card(Rank.TWO, Suit.CLUBS)
                    );
            System.out.println("Your cards are: " + hand);

            final String msg = String.format("You are holding %s", handIdentifier.identify(hand));
            System.out.println(msg);
        } catch (Exception cause) {
            System.out.println("unhandled exception, go fix your code");
            cause.printStackTrace();
        }
    }
}
