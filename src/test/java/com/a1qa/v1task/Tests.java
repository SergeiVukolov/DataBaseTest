package com.a1qa.v1task;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.v1task.models.CredentialsData;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.pages.CompanyPage;
import com.a1qa.v1task.pages.ProjectsPage;
import com.a1qa.v1task.responses.DbResponses;
import com.a1qa.v1task.steps.VariantOneDbSteps;
import com.a1qa.v1task.steps.VariantOneUiApiSteps;
import com.a1qa.v1task.steps.VariantOneUiDbSteps;
import com.a1qa.v1task.utils.*;
import com.a1qa.v1task.utils.Enum;
import org.testng.annotations.Test;

public class Tests extends BaseTest {
    private final Images images = new Images();
    private final ProjectsPage projects = new ProjectsPage();
    private final CompanyPage companyPage = new CompanyPage();
    private final DbResponses dbResponses = new DbResponses();
    private final VariantOneUiApiSteps variantOneUiApiSteps = new VariantOneUiApiSteps();
    private final VariantOneDbSteps variantOneDbSteps = new VariantOneDbSteps();
    private final VariantOneUiDbSteps variantOneUiDbSteps = new VariantOneUiDbSteps();
    private final TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);
    private final CredentialsData credentialsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToCredentials"), CredentialsData.class);

    @Test
    public void variantOneTests() {
        String projectNameTest = RandomString.getRandomString();
        long testId = RandomString.getRandomLong();
        String token = variantOneUiApiSteps.getTokenByVariantNumber();
        variantOneUiApiSteps.assertTokenIsGenerated(token);
        variantOneUiApiSteps.goToWebSiteAndPassAuthorization(
                testsData.getUrl(),
                credentialsData.getLoginWeb(),
                credentialsData.getPasswordWeb());
        variantOneUiApiSteps.projectPageIsDisplayed();
        variantOneUiApiSteps.passTokenUsingCookie(token);
        variantOneUiApiSteps.assertFooterContainsCorrectVariantNumberAfterRefreshing();
        variantOneUiApiSteps.goToPageOfProject(testsData.getProjectName());
        dbResponses.transformData(variantOneDbSteps.getArrayListWithTests(testsData.getProjectName(),
                        companyPage.getCountRowsFromTest()));
        variantOneUiApiSteps.assertSortingTestsOnFirstPage();
        variantOneUiDbSteps.assertTestsFromDbAndUi();
        variantOneUiApiSteps.backToPreviousPageAddProjectAndSave(projectNameTest);
        variantOneUiApiSteps.assertAlertWithSuccessfullySavePresent();
        variantOneUiApiSteps.closeAlertWithSavedProjectAndRefresh();
        variantOneUiApiSteps.assertWindowClosedAndProjectIsInListAfterRefreshing(projectNameTest);
        variantOneUiApiSteps.goToNewProjectPage(projectNameTest);
        int projectNameTestId = projects.getProjectId(projectNameTest);
        variantOneDbSteps.sendTestToDataBase(
                testId,
                RandomString.getRandomString(),
                RandomString.getRandomString(),
                projectNameTestId,
                Enum.THIRTYONE.getValue(),
                RandomString.getRandomString());
        variantOneDbSteps.sendLogToDataBase(
                RandomString.getRandomLong(),
                JsonHelper.getValueFromJson("pathToLogFile"),
                testId);
        images.getScreenShot(JsonHelper.getValueFromJson("pathToScreenShot"));
        variantOneDbSteps.sendAttachmentToDataBase(
                RandomString.getRandomLong(),
                images.createByteFromPng(JsonHelper.getValueFromJson("pathToScreenShot")),
                testsData.getContentType(),
                testId);
        variantOneUiApiSteps.assertIsNewTestPresentInProjectPage(testId);
        variantOneUiApiSteps.goToNewTestPage();
        variantOneUiApiSteps.assertAllFieldsHaveRightValues();
        variantOneUiApiSteps.clickOnScreenShotForComparing();
        images.getScreenShot(JsonHelper.getValueFromJson("pathToScreenShotTwo"));
        variantOneUiApiSteps.assertComparingTwoScreenShots();
    }

}
