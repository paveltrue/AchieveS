package com.a3k.utils.properties;

public class FilepathProperties {

    private static final String separator = System.getProperty("file.separator");

    public final static String HARDIR = System.getProperty("user.dir") + "\\HAR";
    public final static String surefire_screenshots_dir = System.getProperty(("user.dir") + "" + ".\\target\\surefire-reports\\screenshots");
    public final static String local_screenshots_dir = System.getProperty(("user.dir") + "" + ".\\test-output\\screenshots");

    public final static String chromedriverPathWin = (System.getProperty("user" +
            ".dir") + "\\src\\test\\resources\\chromedriver.exe").replaceAll("\\\\|/", "\\" + separator);

    public final static String chromedriverPathUnix = (System.getProperty("user" + ".dir") + "\\src\\test\\resources\\chromedriver").replaceAll("\\\\|/", "\\" + separator);

    public final static String geckodriverPathWin = (System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe").replaceAll("\\\\|/", "\\" + separator);

    public final static String geckodriverPathUnix = (System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver").replaceAll("\\\\|/", "\\" + separator);

}