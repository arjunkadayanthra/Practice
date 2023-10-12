package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final Logger LOG = LoggerFactory.getLogger(Deck.class);
    public static final int CARD_MIN = 1;
    public static final int CARD_MAX = 10;
    public static final int DECK_SIZE = 40;
    Deque<Integer> cards;
    int cuts = 0;


    public Deck() {
        cards = new ArrayDeque<>();
        for (int i = 0; i < DECK_SIZE / CARD_MAX; i++) cards.addAll(getSequence());
        cards = Utilities.shuffleCards(cards);
    }

    private static List<Integer> getSequence() {
        return IntStream.rangeClosed(CARD_MIN, CARD_MAX)
                .boxed().collect(Collectors.toList());
    }

        public void showCards() {
        cards.forEach(x -> LOG.info(x.toString()));
    }

    public Deque<Integer> cut() {
        if (cuts < 2) {
            ArrayList<Integer> cardList = new ArrayList<>(cards);
            cards = new ArrayDeque<>(cardList.subList(0, 20));
            if (cards.size() > 20)
                cards = new ArrayDeque<>(cardList.subList(20, 40));
            cuts++;
        }
        return cards;
    }

}

