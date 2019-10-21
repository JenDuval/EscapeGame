package com.escapegame.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.escapegame.configuration.Memory.*;

public abstract class Sentences {
    private static final Logger logger = LogManager.getLogger(Settings.class);

    public static void read(String lang) {
        switch (lang) {
            case "fr":
                display("DisplayFR.txt");
                break;
            case "en":
                display("DisplayEN.txt");
                break;
        }
    }

    /**
     * Performs file langage : DisplayFR.txt
     */
    public static void display(String file) {
        try {
            String path = "GameplayStudio/src/main/java/com/escapegame/files/" + file;
            InputStream flux = new FileInputStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            String[] splitArray = null;
            while ((line = buff.readLine()) != null) {
                splitArray = line.split(" = ");
                for (int i = 0; i <= 1; i++) {
                    switch (splitArray[i]) {
                        case "Menu":
                            i = i + 1;
                            menu += splitArray[i];
                            menu += "\n";
                            break;
                        case "Win":
                            i = i + 1;
                            winEnd = splitArray[i];
                            break;
                        case "Lose":
                            i = i + 1;
                            loseEnd = splitArray[i];
                            break;
                        case "Equals":
                            i = i + 1;
                            equals = splitArray[i];
                            break;
                        case "End":
                            i = i + 1;
                            theEnd.add(splitArray[i]);
                            break;
                        case "End 1":
                            i = i + 1;
                            theEnd.add(splitArray[i]);
                            break;
                        case "End 2":
                            i = i + 1;
                            theEnd.add(splitArray[i]);
                            break;
                    }
                }
            }
            buff.close();

            diplayStart(path);
            diplaySecret(path);
            displayOther(path);
        } catch (Exception e) {
            logger.debug(e.toString());
        }

    }

    private static void displayOther(String path) {
        try {
            InputStream flux = new FileInputStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            String[] splitArray = null;
            while ((line = buff.readLine()) != null) {
                splitArray = line.split(" = ");
                for (int i = 0; i <= 1; i++) {
                    switch (splitArray[i]) {
                        case "Proposition":
                            i = i + 1;
                            other.add(splitArray[i]);
                            break;
                        case "Proposition 1":
                            i = i + 1;
                            other.add(splitArray[i]);
                            break;
                        case "Response":
                            i = i + 1;
                            other.add(splitArray[i]);
                            break;
                        case "Versus":
                            i = i + 1;
                            versus = splitArray[i];
                            break;
                        case "Defender":
                            i = i + 1;
                            defender = splitArray[i];
                            break;
                        case "Turn":
                            i = i + 1;
                            turn = splitArray[i];
                            break;
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }

    private static void diplaySecret(String path) {
        try {
            InputStream flux = new FileInputStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            String[] splitArray = null;
            while ((line = buff.readLine()) != null) {
                splitArray = line.split(" = ");
                for (int i = 0; i <= 1; i++) {
                    switch (splitArray[i]) {
                        case "Secret":
                            i = i + 1;
                            secret1.add(splitArray[i]);
                            break;
                        case "Secret 1":
                            i = i + 1;
                            secret1.add(splitArray[i]);
                            break;
                        case "Secret 2":
                            i = i + 1;
                            secret1.add(splitArray[i]);
                            break;
                        case "Secret 3":
                            i = i + 1;
                            secret1.add(splitArray[i]);
                            break;
                        case "Secret 4":
                            i = i + 1;
                            secret1.add(splitArray[i]);
                            break;
                    }
                }
            }
            buff.close();


        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }

    private static void diplayStart(String path) {
        try {
            InputStream flux = new FileInputStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String line;
            String[] splitArray = null;
            while ((line = buff.readLine()) != null) {
                splitArray = line.split(" = ");
                for (int i = 0; i <= 1; i++) {
                    switch (splitArray[i]) {
                        case "Start":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 1":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 2":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 3":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 4":
                            i = i + 1;
                            display1 += splitArray[i];
                            display1 += "\n";
                            break;
                        case "Start 5":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 6":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 7":
                            i = i + 1;
                            display2 += splitArray[i];
                            display2 += "\n";
                            break;
                        case "Start 8":
                            i = i + 1;
                            start.add(splitArray[i]);
                            break;
                        case "Start 9":
                            i = i + 1;
                            display3 += splitArray[i];
                            display3 += "\n";
                            break;
                        case "Start 10":
                            i = i + 1;
                            display4 += splitArray[i];
                            display4 += "\n";
                            break;
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }
}
