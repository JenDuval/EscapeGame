package com.escapegame.game;

import com.escapegame.tools.Captures;

import static com.escapegame.configuration.Memory.*;

public class Challenger extends Mod  {
    public static int chiffre;

    public Challenger(){
        super();
        getDisplay();
    }

    @Override
    public void getDisplay(){
        System.out.println(start.get(0) + " " + getNameMod() + "!\n"
                + start.get(1) + "\n"
                + start.get(2) + " " + totalTurn + start.get(3) + "\n"
                + display1
                + start.get(4));

        getTurn();
    }

    @Override
    public void getTurn() {
        IA ia = new IA();

        ia.initialize();
        System.out.println(secret1.get(0) + code);
        for (numberTurn = 1; numberTurn <= totalTurn; numberTurn++) {

            if(develop) {
                String nb = " ";
                for (int i = 0; i < secret; i++)
                    nb += "" + number.get(i);
                System.out.println("(" + secret1.get(0) + nb + ")");
            }

            if (numberTurn == totalTurn) {
                String nb = " ";
                for (int i = 0; i < secret; i++)
                    nb += "" + number.get(i);
                System.out.println(secret1.get(1) + nb);
                this.end(false);
            }
            else {
                chiffre = Captures.readInt(minP, maxP);
                ia.seperat(chiffre);
            }

            if (total.equals(win))
                this.end(true);
            else
                System.out.println("Propostion : " + chiffre + " -> Réponse : " + total);
        }

    }

    @Override
    public String getNameMod() {
        return "challenger";
    }
}
