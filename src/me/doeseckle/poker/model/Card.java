package me.doeseckle.poker.model;

import com.google.common.base.Preconditions;

/**
 * represents a single Card in a deck
 */
public class Card {

    /**
     * ranks available for a card
     */
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    /**
     * suits available for a card
     */
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    private final Rank mRank;
    private final Suit mSuit;

    public Card(Rank rank, Suit suit) {
        mRank = Preconditions.checkNotNull(rank);
        mSuit = Preconditions.checkNotNull(suit);
    }

    /**
     * @return the rank of the card
     */
    public Rank getRank() {
        return mRank;
    }

    /**
     * @return the suit of the card
     */
    public Suit getSuit() {
        return mSuit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", mRank, mSuit);
    }

}
