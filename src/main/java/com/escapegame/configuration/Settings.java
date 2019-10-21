package com.escapegame.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

import static com.escapegame.configuration.Memory.*;

public abstract class Settings {
    private static final Logger logger = LogManager.getLogger(Settings.class);

    /**
     * Choose your configuration file
     */
    public static void readConf(int i) {
        switch (i) {
            case 1:
                fileTXT();
                break;
            case 2:
                fileXML();
                break;
        }
    }

    /**
     * Performs file configuration : configuration.txt
     */
    public static void fileTXT() {
        try {
            InputStream flux = new FileInputStream("src/main/java/com/escapegame/files/Configuration.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);

            String line;
            String maxJ = null;
            String minJ = null;

            String[] splitArray = null;

            while ((line = buff.readLine()) != null) {

                splitArray = line.split(" : ");
                for (int i = 0; i <= 1; i++) {
                    if (splitArray[i].equals("develop")) {
                        i = i + 1;
                        if (splitArray[i].equals("true"))
                            develop = true;
                        else
                            develop = false;
                    } else if (splitArray[i].equals("totalTurn")) {
                        i = i + 1;
                        totalTurn = Integer.parseInt(splitArray[i]);
                    } else if (splitArray[i].equals("secret")) {
                        i = i + 1;
                        secret = Integer.parseInt(splitArray[i]);
                    } else if (splitArray[i].equals("maximum")) {
                        i = i + 1;
                        maximum = Integer.parseInt(splitArray[i]);
                    } else if (splitArray[i].equals("minimum")) {
                        i = i + 1;
                        minimum = Integer.parseInt(splitArray[i]);
                    } else if (splitArray[i].equals("maxP")) {
                        i = i + 1;
                        maxJ = splitArray[i];
                    } else if (splitArray[i].equals("minP")) {
                        i = i + 1;
                        minJ = splitArray[i];
                    }  else if (splitArray[i].equals("lang")) {
                        i = i + 1;
                       lang = splitArray[i];
                    }
                }
            }
            buff.close();

            String tmp = "";
            String tmp2 = "";

            for (int i = 1; i <= secret; i++) {
                win += "=";
                code += "_ ";
                tmp += minJ;
                tmp2 += maxJ;
            }

            maxP = Integer.parseInt(tmp2);
            minP = Integer.parseInt(tmp);
            //System.out.println(develop + "\n" + totalTurn + "\n" + secret + "\n" + maximum + "\n" + minimum + "\n" + maxP + "\n" + minP);
        } catch (Exception e) {
            logger.debug(e.toString());
        }

    }

    /**
     * Performs file configuration : configuration.xml
     */

    public static void fileXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("src\\main\\java\\com\\escapegame\\files\\Configuration.xml");
            Document xml = builder.parse(fileXML);
            Element root = xml.getDocumentElement();
            XPathFactory xpf = XPathFactory.newInstance();
            XPath path = xpf.newXPath();

            String expression = "/config/parametre[1]/valeur";
            String str = (String) path.evaluate(expression, root);
            if (str.equals("true"))
                develop = true;
            else
                develop = false;

            expression = "/config/parametre[2]/valeur";
            str = (String) path.evaluate(expression, root);
            totalTurn = Integer.parseInt(str);

            expression = "/config/parametre[3]/valeur";
            str = (String) path.evaluate(expression, root);
            secret = Integer.parseInt(str);

            expression = "/config/parametre[4]/valeur";
            str = (String) path.evaluate(expression, root);
            maximum = Integer.parseInt(str);

            expression = "/config/parametre[5]/valeur";
            str = (String) path.evaluate(expression, root);
            minimum = Integer.parseInt(str);

            expression = "/config/parametre[6]/valeur";
            str = (String) path.evaluate(expression, root);
            String maxJ = str;

            expression = "/config/parametre[7]/valeur";
            str = (String) path.evaluate(expression, root);
            String minJ = str;

            String tmp = "";
            String tmp2 = "";

            for (int i = 1; i <= secret; i++) {
                win += "=";
                code += "_ ";
                tmp += minJ;
                tmp2 += maxJ;
            }

            maxP = Integer.parseInt(tmp2);
            minP = Integer.parseInt(tmp);
            //System.out.println(develop + "\n" + totalTurn + "\n" + secret + "\n" + maximum + "\n" + minimum + "\n" + maxP + "\n" + minP);

        } catch (ParserConfigurationException e) {
            logger.debug(e);
        } catch (IOException e) {
            logger.debug(e);
        } catch (XPathExpressionException e) {
            logger.debug(e);
        } catch (org.xml.sax.SAXException e) {
            logger.debug(e);
        }
    }
}
