package com.escapegame.game;

import com.escapegame.tools.Captures;

import static com.escapegame.game.IA.iaNumber;
import static com.escapegame.configuration.Memory.*;

public class Defender extends Mod {
    public Defender(){
        super();
        getDisplay();
    }

    @Override
    public void getDisplay() {
        System.out.println(start.get(0)  + " " + getNameMod() + "!\n"
                + start.get(1) + "!\n"
                + start.get(5) + " " + secret + display2
                + start.get(6) + " " + totalTurn + display3
                + start.get(4));

        getTurn();
    }

    @Override
    public void getTurn() {
        IA ia = new IA();

        System.out.println(secret1.get(2));
        numberJ = Captures.readInt(minP,maxP);
        for (numberTurn = 1; numberTurn <= totalTurn; numberTurn++) {
            if (numberTurn == 1) {
                ia.first();
                System.out.println("Propostion de l'ordinateur : " + iaNumber);
                Captures.readString();
            } else {
                ia.beug();
                System.out.println(secret1.get(3) + numberJ + "\n Propostion de l'ordinateur : " + iaNumber );
                Captures.readString();
            }

            if (numberTurn == 5)
                end(true);

            if (total.equals(win))
                end(false);

        }
    }

    @Override
    public String getNameMod() {
        return defender;
    }
}
