package com.escapegame.tools;

import com.escapegame.configuration.Sentences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static com.escapegame.game.Versus.memoryTotal;
import static com.escapegame.configuration.Memory.*;

public class Captures {
    private static final Logger logger = LogManager.getLogger(Captures.class);

    static Scanner sc = new Scanner(System.in);

    /**
     * Allows catcher is processed keyboard input
     *
     * @param min Minimum value
     * @param max Maximum value
     * @return retourne le reste
     */
    public static int readInt(int min, int max) throws IOException {
        Properties prop = null;
        prop = Sentences.load();
        //1) Saisie au clavier
        String line = sc.nextLine();

        //2) Traitement de la ligne et vérifications
        int res;

        //2.1) Vérif n°1 : Si le texte entré n'est pas un entier, on le signale à l'utilisateur et on recommence
        try {
            res = Integer.parseInt(line);
        } catch (Exception e) {
            logger.debug(prop.getProperty("Error"));
            return readInt(min, max);
        }

        //2.2) Vérif n°2 : Si l'entier entré n'est pas entre min et max : on le signale à l'utilisateur et on recommence
        if (res < min || res > max) {
            logger.debug(prop.getProperty("Error"));
            return readInt(min, max);
        }

        //3) Retour de la valeur
        //si la fonction n'a pas été interrompue avant : tout est OK, on retourne le resultat
        return res;
    }

    /**
     * Allows catcher is processed keyboard input
     *
     * @return total
     */
    public static void readString() throws IOException {
        Properties prop = null;
        prop = Sentences.load();
        sc = new Scanner(System.in);

        //1) Saisie au clavier
        String line = sc.nextLine();

        //2) Initialise un array liste et un regex pour split l'array liste
        String[] splitArray = null;

        splitArray = line.split("");

        //3) Vérifie si l'array liste contien bien 4 caractères
        if (splitArray.length == 4) {
            total = "";
            //4.1) Une boucle pour traité l'array liste caractère par caractère
            for (int i = 0; i < splitArray.length; i++) {
                //4.2) Vérifie si l'array liste contien + - ou =
                if (splitArray[i].equals("+"))
                    total += plus;
                else if (splitArray[i].equals("-"))
                    total += moins;
                else if (splitArray[i].equals("="))
                    total += egale;
                else {
                    logger.debug(prop.getProperty("Error"));
                    readString();
                }
                memoryTotal = total;
            }
        } else {
            logger.debug(prop.getProperty("ErrorOne") + splitArray.length);
            for (int i = 0; i < splitArray.length; i++) {
                logger.debug(splitArray[i]);
            }
            readString();
        }
    }
}