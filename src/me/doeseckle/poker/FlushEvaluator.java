package me.doeseckle.poker;

import java.util.Set;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Suit;

import com.google.common.base.Preconditions;

/**
 * evaluates a hand to see if it contains a flush
 */
public class FlushEvaluator {
    private Suit mFlushSuit = null;

    /**
     * evaluates if a had contains a flush
     * 
     * @param hand
     *            the set of cards making up the hand. cannot be null
     * @return true if the hand contains a straight, false otherwise
     */
    public boolean evaluate(Set<Card> hand) {
        Preconditions.checkNotNull(hand, "the hand cannot be null");

        for (Card card : hand) {
            if (mFlushSuit == null) {
                // first card starts the pursuit of a flush
                mFlushSuit = card.getSuit();
                continue;
            }

            if (card.getSuit() != mFlushSuit) {
                return false;
            }
        }

        return true;
    }

}
