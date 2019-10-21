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

public class Defender extends Mod {
    private static final Logger logger = LogManager.getLogger(Defender.class);

    public Defender() {
        super();
        getDisplay();
    }

    @Override
    public String getDisplay() {
        Properties prop = null;
        try {
            prop = Sentences.load();
            Display.write(prop.getProperty("Start") + " " + getNameMod() + "!\n"
                    + prop.getProperty("StartOne") + "\n"
                    + prop.getProperty("StartSix") + " " + secret + prop.getProperty("StartSeven")
                    + prop.getProperty("StartEight") + " " + totalTurn + prop.getProperty("StartNine")
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

            Display.write(prop.getProperty("SecretTwo"));
            numberJ = Captures.readInt(minP, maxP);
            for (numberTurn = 1; numberTurn <= totalTurn; numberTurn++) {
                if (numberTurn == 1) {
                    ia.first();
                    Display.write(prop.getProperty("PropositionOne") + iaNumber);
                    Captures.readString();
                } else {
                    ia.beug();
                    Display.write(prop.getProperty("SecretTwo") + numberJ + "\n" + prop.getProperty("PropositionOne") + iaNumber);
                    Captures.readString();
                }

                if (numberTurn == 5)
                    return end(true);

                if (total.equals(win))
                    return end(false);

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

        return prop.getProperty("Defender");
    }
}
