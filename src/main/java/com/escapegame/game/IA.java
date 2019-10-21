package com.escapegame.game;

import com.escapegame.tools.Captures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

import static com.escapegame.configuration.Memory.*;

public class IA {
    public static String iaNumber;

    private static final Logger logger = LogManager.getLogger(IA.class);

    /**
     * The computer randomly initialize a secret digits secret code
     * in mode challenger
     */
    public void initialize() {
        Random r = new Random();
        for (int i = 0; i < secret; i++) {
            max.add(maximum);
            min.add(minimum);
            number.add(min.get(i) + r.nextInt(max.get(i) - min.get(i)));
        }
    }

    /**
     * Separation in secret digits for compared with the numbers of the secret code of the computer
     * in mode challenger
     *
     * @param number the number making up secret digits of the player
     */
    public void seperat(int number) {
        String line = Integer.toString(number);
        String[] splitArray = null;

        splitArray = line.split("");
        ArrayList<Integer> num = new ArrayList();

        for (int i = 0; i < secret; i++) {
            int nb = Integer.parseInt(splitArray[i]);
            num.add(nb);
        }

        this.balance(num);
    }

    /**
     * Treatment of the ArrayList number
     * in mode challenger
     *
     * @param num the ArrayList number entered by the player
     */
    public void balance(ArrayList<Integer> num) {
        total = "";
        for (int i = 0; i < secret; i++) {
            if (number.get(i) == num.get(i))
                total += egale;
            else if (number.get(i) < num.get(i))
                total += moins;
            else if (number.get(i) > num.get(i))
                total += plus;
        }
    }

    /**
     * Randomly give numbers to find the combination of the player's secret code
     * The first rand to start the game
     * in mode defender
     */
    public void first() {
        Random r = new Random();

        iaNumber = "";
        for (int i = 0; i < secret; i++) {
            max.add(maximum);
            min.add(minimum);
            defNumber.add(min.get(i) + r.nextInt(max.get(i) - min.get(i)));
            iaNumber += "" + defNumber.get(i);
        }
    }

    /**
     * The computer top combination according to the response of the player
     * Change the min, max according to each digit in reference to the answer provided by the player
     * in mode defender
     */
    public void play() {
        Random r = new Random();

        String line = total;
        String[] splitArray = null;
        splitArray = line.split("");

        iaNumber = "";

        for (int i = 0; i < splitArray.length; i++) {
            if (!(splitArray[i].equals("="))) {
                int test = max.get(i) - min.get(i);

                if (splitArray[i].equals("+")) {
                    min.set(i, defNumber.get(i) + 1);
                } else if (splitArray[i].equals("-"))
                    max.set(i, defNumber.get(i) - 1);

                if (test <= 0) {
                    logger.debug("La tricherie ne vous fera pas gagné : Le signe entré en " + i++ + "éme possition donne " + test + " Les valeurs ne peuvent être ni nul, ni négatif");
                    max.set(i, maximum);
                    min.set(i, minimum);
                }

                defNumber.set(i, min.get(i) + r.nextInt(max.get(i) - min.get(i)));
            }
            iaNumber += "" + defNumber.get(i);
        }
    }

    /**
     * Used for error handling
     * in mode defender
     */
    public void beug() {
        try {
            this.play();
        } catch (Exception e) {
            System.out.println("Un chiffre est négatif ou ne correspond plus au max de " + maximum + ", répondez correctement :");
            Captures.readString();
        }

    }
}
