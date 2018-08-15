package com.a3k.utils.properties;

import com.a3k.utils.logger.BasicLogger;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AchieveProperties {

    protected static Logger logger = BasicLogger.getInstance();
    InputStream inputStream;

    Properties props = new Properties();
    private final String propsFileName = "achieve.properties";

    public static String URL;
    public static String gridMode;
    public static String gridHost;
    public static String threadCount;
    public static String browserType;
    public static String headlessMode;
    public static String retriesCount;
    public static String longTimeout;
    public static String shortTimeout;

    public void loadProperties() throws IOException
    {
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream
                    (propsFileName);

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propsFileName
                        + "' not found in the classpath");
            }
            logger.info(props);
            URL =           props.getProperty("envUrl");
            logger.info(URL);
            gridMode     =  props.getProperty("gridMode");
            threadCount  =  props.getProperty("threadCount");
            browserType  =  props.getProperty("browserType");
            gridHost     =  props.getProperty("gridHost");
            headlessMode =  props.getProperty("headlessMode");
            retriesCount =  props.getProperty("retriesCount");
            longTimeout  =  props.getProperty("longWait");
            shortTimeout =  props.getProperty("shortWait");

        } catch (Exception e) {
            logger.trace("Exception: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
