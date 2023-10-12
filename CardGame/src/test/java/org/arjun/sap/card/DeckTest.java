package org.arjun.sap.card;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;


public class DeckTest {

    Deck deck = new Deck();

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Test
    public void testShuffle() {
        assertFalse(deck.cards.contains(11));
        Deck deck2 = new Deck();
        assertTrue(deck.cards.containsAll((Collection<Integer>) deck2.cards));
        assertTrue(deck2.cards.containsAll((Collection<Integer>) deck.cards));
    }

    @Test
    public void testRange() {
        assertFalse(deck.cards.stream().anyMatch(x -> {
            return x > 10;
        }));
        assertFalse(deck.cards.stream().anyMatch(x -> {
            return x < 1;
        }));
        assertEquals(Integer.valueOf(10), Collections.max(deck.cards));
    }

    @Test
    public void testSize() {
        assertEquals(40, deck.cards.size());
    }

    @Test
    public void testShowCards() {
        deck.showCards();
    }

    @Test
    public void testCut(){
        assertNotNull(deck.cut());
        assertEquals(20,deck.cards.size());
        assertNotNull(deck.cut());
        assertEquals(20,deck.cards.size());
        assertNull(deck.cut());
    }
}