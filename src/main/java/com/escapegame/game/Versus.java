package com.escapegame.game;

import com.escapegame.configuration.Sentences;
import com.escapegame.tools.Captures;
import com.escapegame.tools.Display;
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
    public String getDisplay() {
        Properties prop = null;
        try {
            prop = Sentences.load();
            Display.write(prop.getProperty("Start") + getNameMod() + "!\n"
                    + prop.getProperty("StartOne") + "\n"
                    + prop.getProperty("StartTwo") + " " + totalTurn2 + prop.getProperty("StartThree") + "\n"
                    + prop.getProperty("StartTen")
                    + prop.getProperty("StartFive"));

            return getTurn();
        } catch (IOException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public String getTurn() {
        IA ia = new IA();
        Properties prop = null;

        try {
            prop = Sentences.load();
            ia.initialize();
            Display.write(prop.getProperty("SecretTwo"));
            numberJ = Captures.readInt(minP,maxP);
            Display.write(prop.getProperty("SecretFour") + code);
            for (numberTurn = 1; numberTurn <= totalTurn2; numberTurn++) {
                Display.write("=== " + prop.getProperty("Turn") + " N°" + numberTurn + " ===");
                if (numberTurn % 2 == 1) {
                    total = "";
                    chiffre  = Captures.readInt(minP, maxP);
                    ia.seperat(chiffre);
                    Display.write(prop.getProperty("Proposition") + chiffre + prop.getProperty("Response") + total);

                    if (numberTurn == 10)
                        Display.write(prop.getProperty("Equals"));
                    else if (total.equals(win))
                        end(true);
                } else {
                    if (numberTurn == 2) {
                        ia.first();
                        Display.write(prop.getProperty("PropositionOne") + iaNumber);
                        Captures.readString();
                    } else {
                        ia.beug();
                        Display.write(prop.getProperty("SecretThree") + numberJ + "\n" + prop.getProperty("PropositionOne") + iaNumber );
                        Captures.readString();
                    }

                    if (numberTurn == 10)
                        Display.write(prop.getProperty("Equals"));

                    if (total.equals(win))
                        return end(false);
                    total = memoryTotal;
                }
            }
        } catch (IOException e) {
            logger.debug(e);
        }
        return null;
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
