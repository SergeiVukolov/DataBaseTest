package com.a1qa.v1task;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.v1task.models.CredentialsData;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.utils.JsonHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

abstract class BaseTest {
    private CredentialsData credentialsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToCredentials"), CredentialsData.class);
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);
    private Browser browser = AqualityServices.getBrowser();

    @BeforeMethod
    public void setup() {
        browser.maximize();
        browser.goTo(String.format(testsData.getUrl(), credentialsData.getLoginWeb(),
                credentialsData.getPasswordWeb(), testsData.getHostWeb()));
        browser.waitForPageToLoad();
    }

//    @AfterMethod
//    public void tearDown() {
//        browser.quit();
//    }

}
