package org.arjun.sap.card;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class GameTest {

    final Game game = spy(new Game());

    Player p1;
    Player p2;

    @Test
    public void testPlay() {
        Deque<Card> stack = new ArrayDeque<>();
        stack.push(new Card(1));
        stack.push(new Card(2));
        p1 = spy(new Player("1", stack));
        stack = new ArrayDeque<>();
        stack.push(new Card(2));
        stack.push(new Card(2));
        p2 = spy(new Player("2", stack));
        when(p1.play()).thenReturn(new Card(2));
        when(p2.play()).thenReturn(new Card(3));
        game.init();
        doCallRealMethod().when(game).setPlayers(any(), any());
        game.setPlayers(p1, p2);
        Deque<Card> cards = new ArrayDeque<>();
        doCallRealMethod().when(game).playRound(any());
        Player winner = game.playRound(cards);
        verify(game, times(1)).playRound(any());
        assertEquals(p2, winner);
    }

    @Test
    public void testPlayDraw() {
        Deque<Card> stack = new ArrayDeque<>();
        stack.push(new Card(1));
        stack.push(new Card(2));
        p1 = spy(new Player("1", stack));
        stack = new ArrayDeque<>();
        stack.push(new Card(1));
        stack.push(new Card(3));
        p2 = spy(new Player("2", stack));
        when(p1.play()).thenReturn(new Card(1)).thenReturn(new Card(2));
        when(p2.play()).thenReturn(new Card(1)).thenReturn(new Card(3));
        game.init();
        doCallRealMethod().when(game).setPlayers(any(), any());
        game.setPlayers(p1, p2);
        Deque<Card> cards = new ArrayDeque<>();
        doCallRealMethod().when(game).playRound(any());
        Player winner = game.playRound(cards);
//        verify(game, times(2)).playRound(any());
        assertEquals(p2, winner);
    }

    @Test
    public void testInit() {
        Game game = spy(new Game());
        game.init();
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals(20, game.player1.getStackSize());
        assertEquals(20, game.player2.getStackSize());
    }

    @Test
    public void testStart() {
        Game game = spy(new Game());
        game.init();
        game.start();
        verify(game, atLeast(20)).playRound(any());
    }
}