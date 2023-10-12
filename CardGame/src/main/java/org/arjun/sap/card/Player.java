package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Player {
    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    public String getName() {
        return name;
    }

    private final String name;
    Stack<Integer> draw, discard;

    public Player(String name,Stack<Integer> draw) {
        this.name = name;
        this.draw = draw;
        this.discard = new Stack<>();
    }

    public int getStackSize(){
        return draw.size() + discard.size();
    }


    public void setDraw(Stack<Integer> draw) {
        this.draw = draw;
    }


    public void win(List<Integer> cards) {
        discard.addAll(cards);
    }


    public Integer play() {
        if (draw.isEmpty()) {
            if (!discard.isEmpty()) {
                Collections.shuffle(discard);
                setDraw(discard);
                discard = new Stack<>();
                LOG.info("Player {}: Draw pile empty. Shuffled the discard pile into draw pile...",this.getName());
            } else {

            }
        }
        return draw.pop();
    }
}
