package com.a3k.utils.properties;

import com.a3k.utils.logger.BasicLogger;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    protected static Logger logger = BasicLogger.getInstance();
    private InputStream inputStream;

    private Properties props = new Properties();
    private final String propsFileName;

    public PropertiesReader(String fileName) {
        propsFileName = fileName;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream
                    (propsFileName);

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propsFileName
                        + "' not found in the classpath");
            }
            logger.info("Here " + props);
        } catch (Exception e) {
            logger.trace("Exception: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.info(e);
                }

            }
        }
    }

    public String getProperty(String name) {
        logger.info("Prop " + name + " " + props.getProperty(name));
        return props.getProperty(name);
    }
}
