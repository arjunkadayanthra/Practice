package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class Game {
    private static final Logger LOG = LoggerFactory.getLogger(Game.class);
    boolean gameOver = false;

    Player player1;
    Player player2;

    protected void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void init() {
        Deck deck = new Deck();
        player1 = new Player("1", deck.slice());
        player2 = new Player("2", deck.slice());
    }

    public void start() {
        Deque<Card> playedCards = new ArrayDeque<>();
        Player winner;
        do {
            winner = playRound(playedCards);
            winner.win(playedCards);
            playedCards = new ArrayDeque<>();
        } while (!gameOver);
        LOG.debug("Player {} wins the game!", winner.getName());

    }

    protected Player playRound(Deque<Card> round) {
        Player winner;
        int player1StackSize = player1.getStackSize();
        int player2StackSize = player2.getStackSize();
        Card card1 = player1.play();
        Card card2 = player2.play();
        round.add(card1);
        round.add(card2);
        LOG.debug("Player {} ({} cards): {}", player1.getName(), player1StackSize, card1.show());
        LOG.debug("Player {} ({} cards): {}", player2.getName(), player2StackSize, card2.show());
        if (card1.show() > card2.show()) {
            winner = player1;
        } else if (card2.show() > card1.show()) {
            winner = player2;
        } else {
            LOG.debug("No winner in this round\n");
            // For the situation when the last round ends in a draw
            if (player1StackSize == 1 || player2StackSize == 1) {
                // This condition was not specified in the task specification. Hence, not completely tested due to time constraint.
                LOG.error("The game ends with a draw!\n");
                gameOver = true;
                return (player1StackSize == 1 ? player2 : player1);
            }
            return playRound(round);
        }
        LOG.debug("Player {} wins this round\n", winner.getName());
        if ((player1StackSize == 1 && !winner.getName().equals(player1.getName())) || (player2StackSize == 1 && !winner.getName().equals(player2.getName())))
            gameOver = true;
        return winner;
    }
}
