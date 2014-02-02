package me.doeseckle.poker;

import java.util.Set;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;
import me.doeseckle.poker.model.Card.Suit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class TestFlushEvaluator {
    final Card AS = new Card(Rank.ACE, Suit.SPADES);
    final Card KS = new Card(Rank.KING, Suit.SPADES);
    final Card QS = new Card(Rank.QUEEN, Suit.SPADES);
    final Card JS = new Card(Rank.JACK, Suit.SPADES);
    final Card _10S = new Card(Rank.TEN, Suit.SPADES);

    final Card _10D = new Card(Rank.TEN, Suit.DIAMONDS);

    @Test
    public void evaulateWithAFlushHand() {
        final Set<Card> hand = ImmutableSet.of(AS, KS, QS, JS, _10S);

        FlushEvaluator evaluator = new FlushEvaluator();
        Assert.assertTrue(evaluator.evaluate(hand));
    }

    @Test
    public void evaulateWithABrokenFlushHand() {
        final Set<Card> hand = ImmutableSet.of(AS, KS, QS, JS, _10D);

        FlushEvaluator evaluator = new FlushEvaluator();
        Assert.assertFalse(evaluator.evaluate(hand));
    }

}
