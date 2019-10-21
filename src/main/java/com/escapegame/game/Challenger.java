package com.escapegame.game;

import com.escapegame.configuration.Sentences;
import com.escapegame.tools.Captures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static com.escapegame.configuration.Memory.*;

public class Challenger extends Mod  {
    private static final Logger logger = LogManager.getLogger(Challenger.class);
    public static int chiffre;

    public Challenger(){
        super();
        getDisplay();
    }

    @Override
    public void getDisplay(){
        Properties prop = null;
        try {
            prop = Sentences.load();
            System.out.println(prop.getProperty("Start") + " " + getNameMod() + "!\n"
                    + prop.getProperty("StartOne") + "\n"
                    + prop.getProperty("StartTwo") + " " + totalTurn + prop.getProperty("StartThree")  + "\n"
                    + prop.getProperty("StartFour")
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
            System.out.println(prop.getProperty("Secret") + code);
            for (numberTurn = 1; numberTurn <= totalTurn; numberTurn++) {

                if(develop) {
                    String nb = " ";
                    for (int i = 0; i < secret; i++)
                        nb += "" + number.get(i);
                    System.out.println("(" + prop.getProperty("Secret") + nb + ")");
                }

                if (numberTurn == totalTurn) {
                    String nb = " ";
                    for (int i = 0; i < secret; i++)
                        nb += "" + number.get(i);
                    System.out.println(prop.getProperty("SecretOne") + nb);
                    this.end(false);
                }
                else {
                    chiffre = Captures.readInt(minP, maxP);
                    ia.seperat(chiffre);
                }

                if (total.equals(win))
                    this.end(true);
                else
                    System.out.println(prop.getProperty("Proposition") + chiffre + prop.getProperty("Response") + total);
            }
        } catch (IOException e) {
            logger.debug(e);
        }
    }

    @Override
    public String getNameMod() {
        return "challenger";
    }
}
