package me.doeseckle.poker;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * evaluates a hand to determine the best type of hand with multiple cards of the same rank <br>
 * (4-of-a-kind, 3-of-a-kind, two pair, pair, high card) <br>
 * <br>
 * TODO: assumes a 5 card hand. needs logic to handle the possibility of three pairs with a 7 card hand
 */
public class CountEvaluator {

    public String evaluate(Set<Card> hand) {
        Preconditions.checkNotNull(hand, "the hand cannot be null");

        final Map<Rank, AtomicInteger> rankCounters = Maps.newHashMap();

        for (Card card : hand) {
            final AtomicInteger counter = rankCounters.get(card.getRank());

            if (counter == null) {
                // there is not yet a counter for this rank, so create one and set its value to 1
                rankCounters.put(card.getRank(), new AtomicInteger(1));
            } else {
                counter.incrementAndGet();
            }
        }

        Rank highCard = null;
        Rank pairRank = null;
        Rank tripleRank = null;

        for (Entry<Rank, AtomicInteger> entry : rankCounters.entrySet()) {
            switch (entry.getValue().get()) {
                case 1:
                    if (highCard == null || entry.getKey().ordinal() > highCard.ordinal()) {
                        highCard = entry.getKey();
                    }
                    break;
                case 2:
                    if (pairRank != null) {
                        return String.format("two pair, %sS and %sS", pairRank, entry.getKey());
                    } else if (tripleRank != null) {
                        return String.format("full house, %sS over %sS", tripleRank, entry.getKey());
                    } else {
                        pairRank = entry.getKey();
                    }
                    break;
                case 3:
                    if (pairRank != null) {
                        return String.format("full house, %sS over %sS", entry.getKey(), pairRank);
                    } else {
                        tripleRank = entry.getKey();
                    }
                    break;
                case 4:
                    return String.format("four %sS", entry.getKey());
            }

        }

        if (tripleRank != null) {
            return String.format("three %sS", tripleRank);
        }

        if (pairRank != null) {
            return String.format("a pair of %sS", pairRank);
        }

        return String.format("high card %s", highCard);
    }

}
