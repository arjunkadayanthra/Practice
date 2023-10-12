package org.arjun.sap.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Utilities {

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    public static Deque<Card> shuffleCards(Deque<Card> stack) {
        ArrayList<Card> cardList = new ArrayList<>(stack);
        Collections.shuffle(cardList);
        return new ArrayDeque<>(cardList);
    }
}
