package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class configReader {

    private Properties prop;
    public  static String Browser;
    public  static String Environment;
    public  static String qaURL;
    public  static String devURL;

    /**
     * This method is used to load the properties from config.properties file
     * @return it returns Properties prop object
     */
    public void init_prop() {
        loadSystemProperties();
        qaURL = System.getProperty("qaURL");
        devURL = System.getProperty("devURL");
        Browser = System.getProperty("Browser");
        Environment = System.getProperty("Environment");
    }


    public static void loadSystemProperties() {

        try {
            File file = new File("./src/test/resources/Config/Config.properties");

            FileInputStream fileInput = new FileInputStream(file);

            Properties sysProperties = new Properties();

            sysProperties.load(fileInput);

            Enumeration keys = sysProperties.keys();

            while (keys.hasMoreElements()) {

                String key = (String) keys.nextElement();

                String value = sysProperties.getProperty(key);

                System.setProperty(key, value);

            }

        } catch (IOException io) {

            io.printStackTrace();

        }
    }

}





