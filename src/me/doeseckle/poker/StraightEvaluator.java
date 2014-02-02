package me.doeseckle.poker;

import java.util.List;
import java.util.Set;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;

/**
 * evaluates a hand to see if it contains a straight
 */
public class StraightEvaluator {
    private Rank mTopRank = null;

    // supplies the sort key to use for each Card
    private final Function<Card, Rank> mRankProvider = new Function<Card, Rank>() {

        @Override
        public Rank apply(Card card) {
            return card.getRank();
        }

    };

    /**
     * evaluates if a had contains a straight
     * 
     * @param hand
     *            the set of cards making up the hand. cannot be null
     * @return true if the hand contains a straight, false otherwise
     */
    public boolean evaluate(Set<Card> hand) {
        Preconditions.checkNotNull(hand, "the hand cannot be null");

        // sort cards by rank, low to high, using the natural ordering of Rank
        final List<Card> orderedCards = Ordering
                .natural()
                .onResultOf(mRankProvider)
                .sortedCopy(hand);

        // An Ace can be the low card in a Ace-5 straight, but the sort algorithm will always make an Ace the last
        // element in the sorted list, so this case must be handled special
        boolean hasPossibleLowStraight = (orderedCards.get(0).getRank() == Rank.TWO);

        for (Card card : orderedCards) {
            if (mTopRank == null) {
                // first card starts the pursuit of a straight
                mTopRank = card.getRank();
                continue;
            }

            if (card.getRank().ordinal() == mTopRank.ordinal() + 1) {
                // current card is ranked one higher than the previous card, a straight is still possible
                mTopRank = card.getRank();
                continue;
            }

            if (hasPossibleLowStraight && card.getRank() == Rank.ACE) {
                // it is possible this ace is part of a low straight, a straight is still possible
                // but do not adjust the top ranked card, since ACE is low in this straight
                continue;
            }

            // there has been a break in the sequence, and not because of a low straight
            mTopRank = null;
            return false;
        }

        return true;
    }

    /**
     * @return the high ranking card in the straight, or null if there is no straight
     */
    public Rank getTopRank() {
        return mTopRank;
    }

}
