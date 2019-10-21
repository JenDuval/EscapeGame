package com.escapegame.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.escapegame.configuration.Memory.lang;

public abstract class Sentences {


    public static Properties load() throws IOException{
        Properties properties = new Properties();
        String filename = "french.properties";

        switch (lang) {
            case "fr":
                filename = "src/main/ressources/french.properties";
                break;
            case "en":
                filename = "src/main/ressources/english.properties";
                break;
        }
        FileInputStream input = new FileInputStream(filename);
        try{
            properties.load(input);
            return properties;
        }

        finally{
            input.close();
        }
    }
}
