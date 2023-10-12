package org.arjun.sap;

import org.arjun.sap.card.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }
}