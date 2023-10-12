package org.arjun.sap.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Deck {
    private static final Logger LOG = LoggerFactory.getLogger(Deck.class);
    public static final int CARD_MIN = 1;
    public static final int CARD_MAX = 10;
    public static final int DECK_SIZE = 40;
    public static final int MAX_CUTS = 2;
    Deque<Card> cards;
    int cuts = MAX_CUTS;

    public Deck() {
        cards = new ArrayDeque<>();
        for (int i = 0; i < DECK_SIZE / CARD_MAX; i++) cards.addAll(generateCardSet());
        cards = Utilities.shuffleCards(cards);
    }

    private static List<Card> generateCardSet() {
        List<Card> set = new ArrayList<>();
        for (int i = CARD_MIN; i <= CARD_MAX; i++)
            set.add(new Card(i));
        return set;
    }

    public void showAll() {
        cards.forEach(x -> LOG.info(x.show().toString()));
    }

    public Deque<Card> slice() {
        if (cuts > 0) {
            ArrayList<Card> cardList = new ArrayList<>(cards);
            Deque<Card> stack = new ArrayDeque<>(cardList.subList(0, DECK_SIZE / MAX_CUTS));
            if (cards.size() > DECK_SIZE / MAX_CUTS)
                cards = new ArrayDeque<>(cardList.subList(DECK_SIZE / MAX_CUTS, DECK_SIZE));
            else
                cards = new ArrayDeque<>();
            cuts--;
            return stack;
        }
        return cards;
    }

}

