package com.escapegame.game;

import com.escapegame.configuration.Sentences;
import com.escapegame.tools.Captures;
import com.escapegame.tools.Display;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static com.escapegame.configuration.Memory.*;

public abstract class Mod {
    private static final Logger logger = LogManager.getLogger(Mod.class);
    public static int selected;

    /**
     * Displays the menu of the escape game
     * Allows the player to select the desired game mode
     */
    public static void menu() {
        Properties prop = null;
        try {
            prop = Sentences.load();
            Display.write(prop.getProperty("Menu"));
            selected = Captures.readInt(1, 3);
            started(selected);
        } catch (IOException e) {
            logger.debug(e);
        }
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
     * @return
     */
    public static String choice(int select) {
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
        return null;
    }

    /**
     * Display of start of game sentences
     * @return
     */
    public abstract void getDisplay();

    /**
     * A for loop that determines the turns of play
     * The game ends if nbTour and equal to totalTour
     * If the player does not find the combination, endGame is called false
     * If the player found before the nbTour rounds call endGame in true
     * @return
     */
    public abstract void getTurn();


    /**
     * Determines whether the player won or lost at the end of the game
     *
     * @param end true if the player won, false if the player lost
     * @return
     */
    public String end(boolean end){
        Properties prop = null;
        try {
            prop = Sentences.load();

            if (end)
                Display.write(prop.getProperty("Win"));
            else
                Display.write(prop.getProperty("Lose"));

            Display.write("\n"
                    + prop.getProperty("End")
                    + "\n"
                    + prop.getProperty("EndOne")
                    + getNameMod()
                    + " \n"
                    + prop.getProperty("EndTwo"));

            int select = Captures.readInt(1, 4);

            return choice(select);
        } catch (IOException e) {
            logger.debug(e);
        }
        return null;
    }

    public abstract String getNameMod();
}
