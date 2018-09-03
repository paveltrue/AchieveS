package com.a3k.utils.properties;

public class DataBaseProperties {

    private final String fileName = "databaseconfig.properties";
    private final static String L_PORT = "lport";
    private final static String R_PORT = "rport";
    private final static String USER = "user";
    private final static String PASSWORD = "password";
    private final static String HOST = "host";
    private final static String URL = "url";
    private final static String DB_NAME = "dbName";
    private final static String DB_DRIVER = "dbDriver";
    private final static String AUTO_RECONNECRT = "autoReconnect";
    private final static String PORT = "port";
    private final static String USER_NAME = "userName";
    private final static String PASSWORD_1 = "password1";

    public String getPassword1() {
        return prop.getProperty(PASSWORD_1);
    }

    public String getUserName() {
        return prop.getProperty(USER_NAME);
    }

    private PropertiesReader prop;

    public DataBaseProperties() {
        prop = new PropertiesReader(fileName);
    }


    public int getLport() {
        return Integer.parseInt(prop.getProperty(L_PORT));
    }

    public int getRport() {
        return Integer.parseInt(prop.getProperty(R_PORT));
    }

    public String getUser() {
        return prop.getProperty(USER);
    }

    public String getPassword() {
        return prop.getProperty(PASSWORD);
    }

    public String getHost() {
        return prop.getProperty(HOST);
    }

    public String getUrl() {
        return prop.getProperty(URL);
    }

    public String getDbName() {
        return prop.getProperty(DB_NAME);
    }

    public String getDbDriver() {
        return prop.getProperty(DB_DRIVER);
    }

    public String getAutoReconnect() {
        return prop.getProperty(AUTO_RECONNECRT);
    }

    public int getPort() {
        return Integer.parseInt(prop.getProperty(PORT));
    }
}
