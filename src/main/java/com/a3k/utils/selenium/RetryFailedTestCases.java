package com.a3k.utils.selenium;

import com.a3k.utils.logger.BasicLogger;
import com.a3k.utils.properties.AchieveProperties;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

    protected static Logger logger = BasicLogger.getInstance();

    private int retryCount = 0;
    private int maxRetryCount = 0;

    public boolean retry(ITestResult result) {
        maxRetryCount = Integer.parseInt(AchieveProperties
                .retriesCount);
        if (retryCount < maxRetryCount) {
            logger.trace("Retrying test " + result.getName()
                    + " with status "
                    + getResultStatusName(result.getStatus())
                    + " for the " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }
}
