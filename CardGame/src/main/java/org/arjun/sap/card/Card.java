package org.arjun.sap.card;

import java.util.Objects;

public class Card {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(value, card.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Card(Integer value) {
        this.value = value;
    }

    public Integer show() {
        return value;
    }

    final protected Integer value;

}
