package com.escapegame.game;

import com.escapegame.tools.Captures;

import static com.escapegame.game.IA.iaNumber;
import static com.escapegame.configuration.Memory.*;
import static com.escapegame.game.Challenger.chiffre;

public class Versus extends Mod {
    public static String memoryTotal = "";
    private static Integer totalTurn2 = totalTurn * 2;

    public Versus() {
        super();
        getDisplay();
    }

    @Override
    public void getDisplay() {
        System.out.println(start.get(0) + getNameMod() + "!\n"
                + start.get(1) + "\n"
                + start.get(2) + " " + totalTurn2 + start.get(3) + "\n"
                + display4
                + start.get(4));

        getTurn();
    }

    @Override
    public void getTurn() {
        IA ia = new IA();

        ia.initialize();
        System.out.println(secret1.get(2));
        numberJ = Captures.readInt(minP,maxP);
        System.out.println(secret1.get(4) + code);
        for (numberTurn = 1; numberTurn <= totalTurn2; numberTurn++) {
            System.out.println("=== " + turn + " N°" + numberTurn + " ===");
            if (numberTurn % 2 == 1) {
                total = "";
                chiffre  = Captures.readInt(minP, maxP);
                ia.seperat(chiffre);
                System.out.println(other.get(0) + chiffre + other.get(2) + total);

                if (numberTurn == 10)
                    System.out.println(equals);
                else if (total.equals(win))
                    end(true);
            } else {
                if (numberTurn == 2) {
                    ia.first();
                    System.out.println(other.get(1) + iaNumber);
                    Captures.readString();
                } else {
                    ia.beug();
                    System.out.println(secret1.get(3) + numberJ + "\n" + other.get(1) + iaNumber );
                    Captures.readString();
                }

                if (numberTurn == 10)
                    System.out.println(equals);
                else if (total.equals(win))
                    end(false);
                total = memoryTotal;
            }
        }
    }

    @Override
    public String getNameMod() {
        return versus;
    }
}
