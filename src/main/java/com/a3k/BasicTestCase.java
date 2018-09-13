package com.a3k;

import com.a3k.utils.Utils;
import com.a3k.utils.logger.BasicLogger;
import com.a3k.utils.properties.AchieveProperties;
import com.a3k.utils.properties.FilepathProperties;
import com.a3k.utils.selenium.ExtendedSoftAssert;
import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.open;

public class BasicTestCase {

    protected RemoteWebDriver driver;

    protected ExtendedSoftAssert softAssert;
    public static String browserType;
    public static String browserUrl;
    private static String gridTest;
    private static boolean runHeadless;
    private static String gridHost;

    protected static Logger logger = BasicLogger.getInstance();

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "url", "grid"})
    public void setup(Method m,
                      @Optional("chrome") String browser,
                      @Optional("https://portal.achieve3000.com") String url,
                      @Optional("false") String isGrid) throws IOException{

        Utils.writeAllGroupsFromTestsToFile(m);

        logger.trace("Starting test " + m.getName());

        startBrowser(browserType);

        softAssert = new ExtendedSoftAssert(driver);

        navigateToUrl(browserUrl);

        logger.debug("Browser's set up. Starting test.");

    }

    private static void setUpProperties() {

        AchieveProperties properties = new AchieveProperties();
        try {
            properties.loadProperties();
        } catch (IOException e) {
            logger.trace(e.getMessage());
        }

        browserUrl = AchieveProperties.URL;
        logger.info("browser url set from properties file: " + browserUrl);

        browserType = AchieveProperties.browserType;
        logger.info("browser type set from properties file: " + browserType);


        gridTest = AchieveProperties.gridMode;
        gridHost = AchieveProperties.gridHost;

        logger.info("grid mode set from properties file:" + gridTest);
        logger.info("grid host set from properties file:" + gridHost);

        runHeadless = Boolean.parseBoolean(AchieveProperties.headlessMode);
        logger.info("Headless Mode set to " + runHeadless);

    }

    @BeforeTest
    public void beforeSuite(ITestContext context) {

        setUpProperties();

        //TODO refactor this
        if (context.getName().equals("Lexile Grading")) {

            logger.debug("Starting database update.");
           // DatabaseReader db = new DatabaseReader(browserUrl);

            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = new Date(date.getTime() - 24l * 60 * 60 * 1000);
            String time_ended = format.format(date);
            /* update submission dates */
            String query = "update submissions s inner join subscriber_class sc on sc.user_id = s.user_id " + "set Time_ended =  '" + time_ended
                    + "', Score_date =  0, Scored_by =  0, Score = null, Points =  0, Achievement_id =  0 " + "where class_id in (2168149,2168150,2168151,2168152,2168153,2168154,2172571, 2168157, 2168158) "
                    + "and s.time_ended > '2018-06-11'";

            logger.trace("");

            logger.debug("Finished updating database");

        }
        logger.debug("Finished before suite");

    }

    private void startBrowser(String browserType) {

        Configuration.browser = browserType;
        logger.info("Setting the browser up"); // LOG
        if (browserType.equalsIgnoreCase("firefox")) {
            startTestsInFirefox();

        } else if (browserType.equalsIgnoreCase("chrome")) {
            startTestsInChrome();
        }
    }

    private void navigateToUrl(String browserUrl) {
        logger.info("Opening " + browserUrl);
        open("" + browserUrl + "");
    }

    private void startTestsInChrome() {
        logger.info(" Executing on CHROME");

        setChromeCapabilities();

        if (gridTest.equals("true")) {
            //TODO grid with selenide
            //startChromeDriverInGridMode();
        } else {
            startChromeDriverLocally();
        }
    }

    private void setChromeCapabilities() {
        setOSCapabilities();
    }

    private void startChromeDriverLocally() {
        setChromeOptions();
    }

    public WebDriver getDriverSelenium() {
        return this.driver;
    }

    private void startTestsInFirefox() {
        logger.info("Executing on FireFox");

        Configuration.browser = "firefox";

        setFirefoxCapabilities();

        if (gridTest.equals("true")) {
            // TODO grid with selenide
           // startFirefoxDriverInGrid();

        } else {
            startFirefoxDriverLocally();
        }
    }

    private void setFirefoxCapabilities() {
        setOSCapabilities();
    }

    private void setChromeOptions() {
        Configuration.headless = runHeadless;
    }

    public void setOSCapabilities() {
        if (Utils.isMacOS()) {
            setMacOSCapabilities();
        } else if (Utils.isWindows()) {
            setWindowsCapabilities();
        } else if (Utils.isUnix()) {
            setLinuxCapabilities();
        }
    }

    private void setMacOSCapabilities() {
        System.setProperty("webdriver.chrome.driver",
                FilepathProperties.chromedriverPathUnix);
        System.setProperty("webdriver.gecko.driver", FilepathProperties
                .geckodriverPathUnix);
    }

    private void setWindowsCapabilities() {
        System.setProperty("webdriver.chrome.driver", FilepathProperties
                .chromedriverPathWin);
        System.setProperty("webdriver.gecko.driver", FilepathProperties
                .geckodriverPathWin);
    }

    private void setLinuxCapabilities() {
        System.setProperty("webdriver.chrome.driver", FilepathProperties
                .chromedriverPathUnix);
        System.setProperty("webdriver.gecko.driver", FilepathProperties
                .geckodriverPathUnix);
    }

    private void startFirefoxDriverLocally() {
        setFirefoxCapabilities();
    }

    private void setFirefoxOptions() {
        redirectFFLogsToFile();
        Configuration.headless = runHeadless;
    }

    private void redirectFFLogsToFile() {
        String browserLogFile = (System.getProperty
                ("user.dir")
                + "\\src\\test\\resources\\fflog.txt")
                .replaceAll("\\\\|/", "\\"
                        + System.getProperty("file.separator"));
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, browserLogFile);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method m) {
        logger.debug("Finishing test");
        logger.trace("Getting selenium logs");
        logger.debug("Finished test");
    }
}
