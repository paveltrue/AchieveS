package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.DataUploadAssistantPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestDUAProgramSync extends BasicTestCase {

    static DataUploadAssistantPage dua;

    private String districtName = "#Demo Frumie Rosner Prod";
    private final String username = "qa";
    private final String password = "KC7EH4dVYFEdx9aE";

    @Test(groups = {"All", "DUA", "Sync"}, invocationCount = 1)
    public void testSynchronization() {
        openDUATool();
        loginToDUA(username, password);
        openCleverDistrictsTab();
        openDistrict(districtName);
        makeSureDistrictOpened(districtName);
        openProgramTab();
        syncNow();
        acceptAlert();
        turnOffAllAssertionToggles();
        clickContinue();
        verifySyncStarted();
        refreshUntilSynced();
    }

    @Step
    private void openDUATool() {
        open("http://prov11.achieve3000.com/");
        dua = new DataUploadAssistantPage(driver);
        dua.waitForPageToLoad();
    }

    @Step
    private void loginToDUA(String login, String password) {
//        dua.waitForJSandJQueryToLoad();
        dua.fillSignInForm(login, password);
    }

    @Step
    private void openCleverDistrictsTab() {
        dua.openDistrictsTab();
    }

    @Step
    private void openDistrict(String districtName) {
        dua.openDistrict(districtName);
    }

    @Step
    private void makeSureDistrictOpened(String districtName) {
        dua.waitForJSandJQueryToLoad();
        dua.waitForPageToLoad();
        dua.isTextOnPagePresent(districtName);
    }

    @Step
    private void openProgramTab() {
        dua.openProgramSubtab();
    }

    @Step
    private void syncNow() {
        dua.clickSyncNow();
    }

    @Step
    private void acceptAlert() {
        dua.acceptAlert();
    }

    @Step
    private void turnOffAllAssertionToggles() {
        dua.clickDuplicateUserAssertionToggle();
        dua.clickClassChangeAssertionToggle();
        dua.clickUserChangeAssertionToggle();
    }

    @Step
    private void clickContinue() {
        dua.clickContinueBtn();
    }

    @Step
    private void verifySyncStarted() {
        Assert.assertFalse(dua.isTextOnPagePresent("No school selected for Sync"),
                "No school selected for Sync." +
                        " Please select schools you want to sync after click 'SYNC NOW' button.");
        softAssert.assertTrue(dua.isTextOnPagePresent("Sync request has been " +
                "submitted " +
                "successfully"), "The Sync process must be started");
    }

    @Step
    private void refreshUntilSynced() {
        int counter = 10;
        while (!dua.isTextOnPagePresent("Synced") && counter == 0) {
            dua.refreshPage();
            counter--;
        }
    }


}
