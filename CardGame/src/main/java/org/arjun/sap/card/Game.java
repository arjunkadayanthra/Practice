package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final Logger LOG = LoggerFactory.getLogger(Game.class);
    boolean gameOver = false;

    Player player1, player2;

    protected void setPlayers(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void init() {
        Deck deck = new Deck();
        player1 = new Player("1", deck.cut());
        player2 = new Player("2", deck.cut());
    }

    public void start() {
        List<Integer> playedCards = new ArrayList<>();
        Player winner;
        do {
            winner = playRound(playedCards);
            winner.win(playedCards);
            playedCards = new ArrayList<>();
        } while (!gameOver);
        LOG.debug("Player {} wins the game!", winner.getName());

    }

    protected Player playRound(List<Integer> round) {
        boolean draw = false;
        Player winner = new Player("temp",null);
        int player1StackSize = player1.getStackSize();
        int player2StackSize = player2.getStackSize();
        Integer card1 = player1.play();
        Integer card2 = player2.play();
        round.add(card1);
        round.add(card2);
        LOG.debug("Player {} ({} cards): {}", player1.getName(), player1StackSize, card1);
        LOG.debug("Player {} ({} cards): {}", player2.getName(), player2StackSize, card2);
        if (card1 > card2) {
            winner = player1;
        } else if (card2 > card1) {
            winner = player2;
        } else {
            LOG.debug("No winner in this round\n");
            draw = true;
            // For the situation when the last round ends in a draw
            if (player1StackSize == 1 || player2StackSize == 1) {
                // This condition was not specified in the task specification. Hence not completely tested due to time constraint.
                LOG.error("The game ends with a draw!\n");
                gameOver = true;
                return (player1StackSize == 1 ? player2 : player1);
            }
            return playRound(round);
        }
        LOG.debug("Player {} wins this round\n", winner.getName());
        if ((player1StackSize == 1 && !winner.getName().equals(player1.getName())) || (player2StackSize == 1 && !winner.getName().equals(player2.getName()))) {
            gameOver = true;
        }
        return winner;
    }
}