package org.arjun.sap.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Utilities {

    public static Deque<Integer> shuffleCards(Deque<Integer> stack) {
        ArrayList<Integer> cardList = new ArrayList<>(stack);
        Collections.shuffle(cardList);
        return new ArrayDeque<>(cardList);
    }
}
