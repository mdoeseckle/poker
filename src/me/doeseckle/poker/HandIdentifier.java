package me.doeseckle.poker;

import java.util.Set;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;

import com.google.common.base.Preconditions;

/**
 * identifies the best poker hand using the provided cards
 */
public class HandIdentifier {

    /**
     * identifies the quality of the specified Poker hand
     * 
     * @param hand
     *            the set of cards that makes up the hand. value cannot be null.
     * @return the quality of the specified hand.
     */
    public String identify(Set<Card> hand) {
        Preconditions.checkNotNull(hand);
        Preconditions.checkState(hand.size() <= 5, "currently only supports up to five card hands");

        final FlushEvaluator flushEvaluator = new FlushEvaluator();
        final boolean hasFlush = flushEvaluator.evaluate(hand);

        final StraightEvaluator straightEvaluator = new StraightEvaluator();
        final boolean hasStraight = straightEvaluator.evaluate(hand);

        if (hasStraight && hasFlush) {
            return (straightEvaluator.getTopRank() == Rank.ACE)
                    ? "a royal flush"
                    : String.format("a straight flush to the %s", straightEvaluator.getTopRank());
        }

        if (hasFlush) {
            return "a flush";
        }

        if (hasStraight) {
            return String.format("a straight to the %s", straightEvaluator.getTopRank());
        }

        final CountEvaluator countEvaluator = new CountEvaluator();
        return countEvaluator.evaluate(hand);
    }

}
