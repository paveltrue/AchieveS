package com.a3k.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.regex.Matcher;

public class BasicLogger {

    private static Logger instance = null;

    private BasicLogger() {
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            loadConfiguration();
            instance = LogManager.getLogger();
        }
        return instance;
    }

    private static void loadConfiguration() {

        String config = System.getProperty("user.dir") +
                "\\src\\main\\resources\\log4j2.xml";
        config = config.replaceAll("[/\\\\]+", Matcher.quoteReplacement(System.
                getProperty("file.separator")));

        Configurator.initialize(null, config);

    }
}
