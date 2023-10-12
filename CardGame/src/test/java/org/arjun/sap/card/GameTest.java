package org.arjun.sap.card;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class GameTest {

    Game game = spy(new Game());

    Player p1;
    Player p2;

    @Test
    public void testPlay() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        p1 = spy(new Player("1", stack));
        stack = new Stack<>();
        stack.push(2);
        stack.push(2);
        p2 = spy(new Player("2", stack));
        when(p1.play()).thenReturn(2);
        when(p2.play()).thenReturn(3);
        game.init();
        doCallRealMethod().when(game).setPlayers(any(), any());
        game.setPlayers(p1, p2);
        List<Integer> cards = new ArrayList<>();
        doCallRealMethod().when(game).playRound(any());
        Player winner = game.playRound(cards);
        verify(game, times(1)).playRound(any());
        assertEquals(p2, winner);
    }

    @Test
    public void testPlayDraw() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        p1 = spy(new Player("1", stack));
        stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        p2 = spy(new Player("2", stack));
        when(p1.play()).thenReturn(1).thenReturn(2);
        when(p2.play()).thenReturn(1).thenReturn(3);
        game.init();
        doCallRealMethod().when(game).setPlayers(any(), any());
        game.setPlayers(p1, p2);
        List<Integer> cards = new ArrayList<>();
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
        assertEquals(20,game.player1.getStackSize());
        assertEquals(20,game.player2.getStackSize());
    }

    @Test
    public void testStart() {
        Game game = spy(new Game());
        game.init();
        game.start();
        verify(game, atLeast(20)).playRound(any());
    }
}