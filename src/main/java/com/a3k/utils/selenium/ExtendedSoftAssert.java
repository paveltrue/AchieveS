package com.a3k.utils.selenium;

import com.a3k.utils.logger.BasicLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;


public class ExtendedSoftAssert extends SoftAssert {
    //TODO do it all with selenide
    private WebDriver driver;
    private Calendar c;
    private static final String separator = System.getProperty("file.separator");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    protected static Logger basicLogger = BasicLogger.getInstance();

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {

        super.onAssertFailure(assertCommand, ex);

        takeScreenshot(assertCommand.getMessage(), ex.getClass().getSimpleName());

        basicLogger.error(assertCommand.getMessage());

    }

    public ExtendedSoftAssert(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void takeScreenshot(String message, String clName) {

        try {
            if (driver != null && ((RemoteWebDriver) driver).getSessionId() != null) {
                basicLogger.debug("Taking screenshot on failure");
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                c = Calendar.getInstance();

                String path = System.getProperty("user.dir")
                        + separator
                        + "target"
                        + separator
                        + "surefire-reports"
                        + separator
                        + "screenshots"
                        + separator
                        + clName
                        + "_"
                        + sdf.format(c.getTime())
                        + ".png";


                FileUtils.copyFile(scrFile, new File((path)));
                path = System.getProperty("user.dir")
                        + separator
                        + "screenshots"
                        + separator
                        + clName + "_" + sdf.format(c.getTime()) +
                        ".png";

                path = path.replaceAll("[/\\\\]+", Matcher.quoteReplacement(System.
                        getProperty("file.separator")));
                FileUtils.copyFile(scrFile, new File((path)));


                makeScreenshot();
                Reporter.log("<br>" +
                        " <a href=./screenshots/" +
                        clName + "_" +
                        sdf.format(c.getTime()) +
                        ".png>(Assert fail) click to open screenshot of " +
                        message +
                        "</a> " +
                        "<br>");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Attachment(type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}

