package com.escapegame.game;

import com.escapegame.configuration.Sentences;
import com.escapegame.tools.Captures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static com.escapegame.game.IA.iaNumber;
import static com.escapegame.configuration.Memory.*;
import static com.escapegame.game.Challenger.chiffre;

public class Versus extends Mod {
    public static String memoryTotal = "";
    private static Integer totalTurn2 = totalTurn * 2;
    private static final Logger logger = LogManager.getLogger(Versus.class);

    public Versus() {
        super();
        getDisplay();
    }

    @Override
    public void getDisplay() {
        Properties prop = null;
        try {
            prop = Sentences.load();
            System.out.println(prop.getProperty("Start") + getNameMod() + "!\n"
                    + prop.getProperty("StartOne") + "\n"
                    + prop.getProperty("StartTwo") + " " + totalTurn2 + prop.getProperty("StartThree") + "\n"
                    + prop.getProperty("StartTen")
                    + prop.getProperty("StartFive"));

            getTurn();
        } catch (IOException e) {
            logger.debug(e);
        }
    }

    @Override
    public void getTurn() {
        IA ia = new IA();
        Properties prop = null;

        try {
            prop = Sentences.load();
            ia.initialize();
            System.out.println(prop.getProperty("SecretTwo"));
            numberJ = Captures.readInt(minP,maxP);
            System.out.println(prop.getProperty("SecretFour") + code);
            for (numberTurn = 1; numberTurn <= totalTurn2; numberTurn++) {
                System.out.println("=== " + prop.getProperty("Turn") + " N°" + numberTurn + " ===");
                if (numberTurn % 2 == 1) {
                    total = "";
                    chiffre  = Captures.readInt(minP, maxP);
                    ia.seperat(chiffre);
                    System.out.println(prop.getProperty("Proposition") + chiffre + prop.getProperty("Response") + total);

                    if (numberTurn == 10)
                        System.out.println(prop.getProperty("Equals"));
                    else if (total.equals(win))
                        end(true);
                } else {
                    if (numberTurn == 2) {
                        ia.first();
                        System.out.println(prop.getProperty("PropositionOne") + iaNumber);
                        Captures.readString();
                    } else {
                        ia.beug();
                        System.out.println(prop.getProperty("SecretThree") + numberJ + "\n" + prop.getProperty("PropositionOne") + iaNumber );
                        Captures.readString();
                    }

                    if (numberTurn == 10)
                        System.out.println(prop.getProperty("Equals"));
                    else if (total.equals(win))
                        end(false);
                    total = memoryTotal;
                }
            }
        } catch (IOException e) {
            logger.debug(e);
        }
    }

    @Override
    public String getNameMod() {
        Properties prop = null;
        try {
            prop = Sentences.load();
        } catch (IOException e) {
            logger.debug(e);
        }

        return prop.getProperty("Versus");
    }
}
