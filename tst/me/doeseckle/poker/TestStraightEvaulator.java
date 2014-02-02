package me.doeseckle.poker;

import java.util.Set;

import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;
import me.doeseckle.poker.model.Card.Suit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class TestStraightEvaulator {
    final Card AS = new Card(Rank.ACE, Suit.SPADES);
    final Card KS = new Card(Rank.KING, Suit.SPADES);
    final Card QS = new Card(Rank.QUEEN, Suit.SPADES);
    final Card JS = new Card(Rank.JACK, Suit.SPADES);
    final Card _10S = new Card(Rank.TEN, Suit.SPADES);
    final Card _2D = new Card(Rank.TWO, Suit.DIAMONDS);
    final Card _3D = new Card(Rank.THREE, Suit.DIAMONDS);
    final Card _4C = new Card(Rank.FOUR, Suit.CLUBS);
    final Card _5C = new Card(Rank.FIVE, Suit.CLUBS);

    @Test
    public void evaulateWithHighStraight() {
        final Set<Card> hand = ImmutableSet.of(AS, KS, _10S, JS, QS);
        final StraightEvaluator evaluator = new StraightEvaluator();
        Assert.assertTrue(evaluator.evaluate(hand));
    }

    @Test
    public void evaulateWithLowStraight() {
        final Set<Card> hand = ImmutableSet.of(_4C, _5C, AS, _3D, _2D);
        final StraightEvaluator evaluator = new StraightEvaluator();
        Assert.assertTrue(evaluator.evaluate(hand));
    }

    @Test
    public void evaulateWithBrokenStraight() {
        final Set<Card> hand = ImmutableSet.of(_4C, _5C, AS, KS, _2D);
        final StraightEvaluator evaluator = new StraightEvaluator();
        Assert.assertFalse(evaluator.evaluate(hand));
    }

}
