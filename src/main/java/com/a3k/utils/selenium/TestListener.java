package com.a3k.utils.selenium;

import com.a3k.BasicTestCase;
import com.a3k.utils.logger.BasicLogger;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestListener extends TestListenerAdapter {
    private WebDriver driver;
    protected static Logger logger = BasicLogger.getInstance();

    private static final String separator = System.getProperty("file.separator");

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        driver = ((BasicTestCase) currentClass).getDriverSelenium();
        Calendar c = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat
                ("dd_MM_yyyy_hh_mm_ss");


        try {
            if (driver != null && result.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                String path =
                        System.getProperty("user.dir")
                                + separator
                                + "target"
                                + separator
                                + "surefire-reports"
                                + separator
                                + "screenshots"
                                + separator
                                + formatter.format(c.getTime()) + ".png";

               FileUtils.copyFile(scrFile, new File((path)));


                path = System.getProperty("user.dir")
                        + separator
                        + "screenshots"
                        + separator
                        + formatter.format(c.getTime()) + ".png";

                FileUtils.copyFile(scrFile, new File((path)));

                makeScreenshotForAllure();

                Reporter.log("<br> " +
                        "<a href=./screenshots//"
                        + formatter.format(c.getTime())
                        + ".png>(Exception)click to open screenshot of "
                        + result.getInstanceName() + "</a> <br>");


                try {
                    waitAndAcceptAlertIfExist();
                    driver.quit();
                } catch (Exception e) {
                    logger.trace(e.getMessage());
                }
                logger.trace("====== " + result.getInstanceName()
                        + "."
                        + result.getMethod().getMethodName()
                        + getTestParameters(result)
                        + " FAILED=====");
            }
        } catch (Exception e) {
//        	waitAndAcceptAlertIfExist();
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        Object currentClass = result.getInstance();
        driver = ((BasicTestCase) currentClass).getDriverSelenium();

        logger.trace("====== " + result.getInstanceName()
                + "."
                + result.getMethod().getMethodName()
                + getTestParameters(result)
                + " SKIPPED =====");
        try {
            waitAndAcceptAlertIfExist();
            driver.quit();
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        driver = ((BasicTestCase) currentClass).getDriverSelenium();
        logger.trace("====== " + result.getInstanceName()
                + "."
                + result.getMethod().getMethodName()
                + getTestParameters(result)
                + " PASSED =====");
        try {
            waitAndAcceptAlertIfExist();
            driver.quit();
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }
    }

    private String getTestParameters(ITestResult result) {
        StringBuilder parameters = new StringBuilder("(");
        if (result.getParameters() != null && result.getParameters().length != 0) {
            for (int i = 0; i < result.getParameters().length; i++) {
                parameters.append("\"");
                parameters.append(result.getParameters()[i]);
                parameters.append(i == result.getParameters().length - 1 ? "\"" : "\", ");
            }
        }
        parameters.append(")");
        return parameters.toString();
    }

    @Attachment(type = "image/png")
    public byte[] makeScreenshotForAllure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

	public void waitAndAcceptAlertIfExist() {
		try {
			if (isAlertPresent()) {
				WebDriverWait wait = new WebDriverWait(driver, 4);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();
				driver.switchTo().defaultContent();
			}
		} catch (Exception e) {

		}
	}

	public boolean isAlertPresent() {
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}


}