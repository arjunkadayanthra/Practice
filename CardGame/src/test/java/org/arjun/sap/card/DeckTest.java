package org.arjun.sap.card;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeckTest {

    Deck deck = new Deck();

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @BeforeClass
    public static void beforeClass() {

    }

    @Test
    public void testShuffle() {
        Deck deck2 = new Deck();
        assertTrue(deck.cards.containsAll(deck2.cards));
        assertTrue(deck2.cards.containsAll(deck.cards));
    }

    @Test
    public void testRange() {
        assertFalse(deck.cards.stream().anyMatch(x -> x.show() > 10));
        assertFalse(deck.cards.stream().anyMatch(x -> x.show() < 1));
    }

    @Test
    public void testSize() {
        assertEquals(40, deck.cards.size());
    }

    @Test
    public void testShowCards() {
        deck.showAll();
        assertEquals(40, deck.cards.size());
    }

    @Test
    public void testCut() {
        assertNotNull(deck.slice());
        assertEquals(20, deck.cards.size());
        assertEquals(20, deck.slice().size());
        assertTrue(deck.slice().isEmpty());
        assertEquals(0, deck.cards.size());
    }
}