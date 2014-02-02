package me.doeseckle.poker;

import java.util.Set;

import junit.framework.Assert;
import me.doeseckle.poker.model.Card;
import me.doeseckle.poker.model.Card.Rank;
import me.doeseckle.poker.model.Card.Suit;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class TestCountEvaluator {
    final Card AS = new Card(Rank.ACE, Suit.SPADES);
    final Card AD = new Card(Rank.ACE, Suit.DIAMONDS);
    final Card AC = new Card(Rank.ACE, Suit.CLUBS);
    final Card AH = new Card(Rank.ACE, Suit.HEARTS);

    final Card KS = new Card(Rank.KING, Suit.SPADES);
    final Card JD = new Card(Rank.JACK, Suit.DIAMONDS);
    final Card _8D = new Card(Rank.EIGHT, Suit.DIAMONDS);
    final Card _5H = new Card(Rank.FIVE, Suit.HEARTS);
    final Card _5C = new Card(Rank.FIVE, Suit.CLUBS);
    final Card _2C = new Card(Rank.TWO, Suit.CLUBS);

    @Test
    public void evaluateWithFourOfAKind() {
        final Set<Card> hand = ImmutableSet.of(AS, AD, _5H, AC, AH);
        final CountEvaluator evaluator = new CountEvaluator();

        Assert.assertEquals("four ACES", evaluator.evaluate(hand));
    }

    @Test
    public void evaluateWithAFullHouse() {
        final Set<Card> hand = ImmutableSet.of(AS, _5C, _5H, AC, AH);
        final CountEvaluator evaluator = new CountEvaluator();

        Assert.assertEquals("full house, ACES over FIVES", evaluator.evaluate(hand));
    }

    @Test
    public void evaluateWithThreeOfAKind() {
        final Set<Card> hand = ImmutableSet.of(AS, _2C, _5H, AC, AH);
        final CountEvaluator evaluator = new CountEvaluator();

        Assert.assertEquals("three ACES", evaluator.evaluate(hand));
    }

    @Test
    public void evaluateWithTwoPair() {
        final Set<Card> hand = ImmutableSet.of(AS, _5C, _5H, _2C, AH);
        final CountEvaluator evaluator = new CountEvaluator();

        final String validResult1 = "two pair, ACES and FIVES";
        final String validResult2 = "two pair, FIVES and ACES";
        final String actual = evaluator.evaluate(hand);

        Assert.assertTrue(validResult1.equals(actual) || validResult2.equals(actual));
    }

    @Test
    public void evaluateWithOnePair() {
        final Set<Card> hand = ImmutableSet.of(AS, _2C, _5H, AC, _8D);
        final CountEvaluator evaluator = new CountEvaluator();

        Assert.assertEquals("a pair of ACES", evaluator.evaluate(hand));
    }

    @Test
    public void evaluateWithHighCard() {
        final Set<Card> hand = ImmutableSet.of(KS, _2C, _5H, JD, _8D);
        final CountEvaluator evaluator = new CountEvaluator();

        Assert.assertEquals("high card KING", evaluator.evaluate(hand));
    }

}
