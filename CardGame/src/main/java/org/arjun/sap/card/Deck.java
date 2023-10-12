package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final Logger LOG = LoggerFactory.getLogger(Deck.class);
    public static final int CARD_MIN = 1;
    public static final int CARD_MAX = 10;
    public static final int DECK_SIZE = 40;
    List<Integer> cards;
    int cuts=0;


    public Deck() {
        cards = new ArrayList<>();
        for (int i = 0; i < DECK_SIZE/CARD_MAX; i++) cards.addAll(getSequence());
        shuffle();
    }

    private static List<Integer> getSequence() {
        return IntStream.rangeClosed(CARD_MIN, CARD_MAX)
                .boxed().collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void showCards(){
        cards.forEach(System.out::println);
    }

    public Stack<Integer> cut() {
       if(cuts < 2) {
           Stack<Integer> stack = new Stack<>();
           stack.addAll(cards.subList(0, 20));
           if(cards.size() > 20)
               cards = cards.subList(20, 40);
           cuts++;
           return stack;
       }
        return null;
    }

}
