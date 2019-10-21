package com.escapegame.game;

import com.escapegame.tools.Captures;

import static com.escapegame.configuration.Memory.*;

public abstract class Mod {
    public static int selected;

    /**
     * Displays the menu of the escape game
     * Allows the player to select the desired game mode
     */
    public static void menu() {
        System.out.println(menu);

        selected = Captures.readInt(1, 3);
        started(selected);
    }

    /**
     * Starts the escape according to the desired game mode
     *
     * @param select The number corresponding to the start of the different game mode
     * @return
     */
    public static Mod started(int select) {
        if (select == 1)
            return new Challenger();
        else if (select == 2)
            return new Defender();
        else if (select == 3)
            return new Versus();
        else
            return null;
    }

    /**
     * The switch that allows the action chosen by the player
     *
     * @param select select the choice made by the player at the end of the game
     */
    public static void choice(int select) {
        switch (select) {
            case 1:
                started(selected);
                break;
            case 2:
                menu();
                break;
            case 3:
                numberTurn = totalTurn;
                break;
        }
    }

    /**
     * Display of start of game sentences
     */
    public abstract void getDisplay();

    /**
     * A for loop that determines the turns of play
     * The game ends if nbTour and equal to totalTour
     * If the player does not find the combination, endGame is called false
     * If the player found before the nbTour rounds call endGame in true
     */
    public abstract void getTurn();


    /**
     * Determines whether the player won or lost at the end of the game
     *
     * @param end true if the player won, false if the player lost
     */
    public void end(boolean end){
        if (end)
            System.out.println(winEnd);
        else
            System.out.println(loseEnd);
        System.out.println("");
        System.out.println(theEnd.get(0));
        System.out.println("");
        System.out.println(theEnd.get(1) + getNameMod() + " \n"
                + theEnd.get(2) + " \n"
                + theEnd.get(3) + " \n");

        int select = Captures.readInt(1, 4);

        Mod.choice(select);
    }

    public abstract String getNameMod();
}
