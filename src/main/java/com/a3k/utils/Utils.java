package com.a3k.utils;

import com.a3k.utils.logger.BasicLogger;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    private static String OS = null;

    protected static Logger logger = BasicLogger.getInstance();

    protected static String killGeckodriversWin = "cmd /c Taskkill /IM " +
            "geckodriver.exe /F";
    protected static String killGeckodriversMac = "killall -kill geckodriver";

    protected static String killFirefoxesWin = "cmd /c Taskkill /IM firefox" +
            ".exe /F";

    protected static String killFirefoxesMac = "killall - kill firefox";

    protected static String killChromedriversWin = "cmd /c Taskkill /IM " +
            "chromedriver.exe /F";

    protected static String killChromedriversMac = "killall -kill chromedriver";

    protected static String killChromeWin = "cmd /c Taskkill /IM chrome.exe /F";

    protected static String killChromeMac = "killall -kill chrome";


    private final static String fileWithTestGroups = (System.getProperty
            ("user.dir")
            + "\\src\\test\\resources\\testgroups.txt")
            .replaceAll("\\\\|/", "\\"
                    + System.getProperty("file.separator"));

    private static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix() {
        return getOsName().startsWith("Linux");
    }

    public static boolean isMacOS() {
        return getOsName().contains("Mac");
    }

    public static void writeAllGroupsFromTestsToFile(Method m) throws
            IOException {
        Test t = m.getAnnotation(Test.class);

        BufferedWriter bw = new BufferedWriter(new FileWriter
                (fileWithTestGroups, true));

        for (int i = 0; i < t.groups().length; i++) {
            bw.append(t.groups()[i].trim());
            bw.append("\n");
        }

        bw.close();

    }

    public static void removeDuplicatesFromGroups() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader
                (fileWithTestGroups));

        String line;
        Set<String> testGroups = new HashSet<String>();
        while ((line = br.readLine()) != null) {
            testGroups.add(line);
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter
                (fileWithTestGroups));

        for (String group : testGroups) {
            bw.write(group);
            bw.newLine();
        }

        bw.close();
    }

    private static void killAllChromeDrivers() {
        String cmd = "";
        if (isWindows()) {
            cmd = killChromedriversWin;
        } else if (isMacOS()) {
            cmd = killChromedriversMac;
        }
        executeCommand(cmd);
    }

    private static void killAllChromeBrowsers() {
        String cmd = "";
        if (isWindows()) {
            cmd = killChromeWin;
        } else if (isMacOS()) {
            cmd = killChromeMac;
        }
        executeCommand(cmd);
    }

    private static void killAllGeckoDrivers() {
        String cmd = "";
        if (isWindows()) {
            cmd = killGeckodriversWin;
        } else if (isMacOS()) {
            cmd = killGeckodriversMac;
        }
        executeCommand(cmd);

    }

    private static void killAllFirefoxes() {
        String cmd = "";
        if (isWindows()) {
            cmd = killFirefoxesWin;
        } else if (isMacOS()) {
            cmd = killFirefoxesMac;
        }
        executeCommand(cmd);
    }

    public static void killBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            killAllFirefoxes();
            killAllGeckoDrivers();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            killAllChromeBrowsers();
            killAllChromeDrivers();
        }
    }

    private static void executeCommand(String cmd) {

        logger.info("Executing cmd: " + cmd);
        try {
            Process proc = Runtime.getRuntime().exec(cmd);

            // Get input streams
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // Read command standard output
            String s;
            logger.trace("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                logger.trace(s);
            }

            // Read command errors
            logger.trace("Standard error: ");
            while ((s = stdError.readLine()) != null) {
                logger.trace(s);
            }
        } catch (IOException e) {
            logger.error("Unable to execute command '" + cmd + "'\n " +
                    System.err);
        }
    }
}
