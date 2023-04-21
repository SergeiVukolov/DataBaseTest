package com.a1qa.v1task;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.pages.Projects;
import com.a1qa.v1task.responses.ApiResponses;
import com.a1qa.v1task.steps.DbSteps;
import com.a1qa.v1task.utils.Authorization;
import com.a1qa.v1task.utils.JsonHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends BaseTest {
    private Browser browser = AqualityServices.getBrowser();
    DbSteps dbSteps = new DbSteps();
    Projects projects = new Projects();
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    @Test
    public void tests() {
        Assert.assertFalse(ApiResponses.getTokenResponse().isEmpty(), "Token is null");
        Authorization.sendTokenAsCookie(ApiResponses.getTokenResponse());
        browser.refresh();
        Assert.assertTrue(projects.getVersionNumber().contains(testsData.getVersion().toString()), "Version is inconsistent");
    }

}
