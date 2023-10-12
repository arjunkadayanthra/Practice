package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player {
    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    public String getName() {
        return name;
    }

    private final String name;
    Deque<Integer> draw;
    Deque<Integer> discard;

    public Player(String name, Deque<Integer> draw) {
        this.name = name;
        this.draw = draw;
        this.discard = new ArrayDeque<>();
    }

    public int getStackSize() {
        return draw.size() + discard.size();
    }


    public void setDraw(Deque<Integer> draw) {
        this.draw = draw;
    }


    public void win(Deque<Integer> cards) {
        discard.addAll(cards);
    }


    public Integer play() {
        if (draw.isEmpty() && (!discard.isEmpty())) {
            setDraw(Utilities.shuffleCards(discard));
            discard = new ArrayDeque<>();
            LOG.info("Player {}: Draw pile empty. Shuffled the discard pile into draw pile...", this.getName());

        }
        return draw.pop();
    }
}
