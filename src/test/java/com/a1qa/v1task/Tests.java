package com.a1qa.v1task;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.pages.Company;
import com.a1qa.v1task.pages.CompanyBuilder;
import com.a1qa.v1task.pages.Projects;
import com.a1qa.v1task.responses.ApiResponses;
import com.a1qa.v1task.responses.DbResponses;
import com.a1qa.v1task.steps.DbSteps;
import com.a1qa.v1task.utils.*;
import com.a1qa.v1task.utils.Enum;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends BaseTest {
    private Browser browser = AqualityServices.getBrowser();
    DbSteps dbSteps = new DbSteps();
    Images images = new Images();
    Projects projects = new Projects();
    Company company = new Company();
    Utility utility = new Utility();
    CompanyBuilder companyBuilder = new CompanyBuilder();
    DbResponses dbResponses = new DbResponses();
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    @Test
    public void tests() {
        String projectNameTest = RandomString.getRandomString();
        long testId = RandomString.getRandomLong();
        Assert.assertFalse(ApiResponses.getTokenResponse().isEmpty(), "Token is null");
        Authorization.sendTokenAsCookie(ApiResponses.getTokenResponse());
        browser.refresh();
        Assert.assertTrue(projects.getTextFromPanelHeading().contains(testsData.getTextProjects()), "It isn't projects page");
        Assert.assertTrue(projects.getVersionNumber().contains(testsData.getVersion().toString()), "Version is inconsistent");
//        projects.clickOnProjectLabel(testsData.getProjectName());
//        Assert.assertTrue(company.checkDates(), "Tests aren't sorted by date");
//        Assert.assertEquals(company.getDataTable(), dbResponses.transformData(testsData.getProjectName(), company.getCountRowsFromTest()), "List from DataBase isn't included List from UI");
//        company.backToProjectsPage();
        projects.clickAddButton();
        utility.switchToIframe();
        companyBuilder.inputProjectName(projectNameTest);
        companyBuilder.saveProject();
        Assert.assertTrue(companyBuilder.informationAboutSave().contains(testsData.getSuccess()), "Project isn't saved");
        Assert.assertTrue(companyBuilder.getWordFromAlert().contains(testsData.getWordAlert()), "It isn't message in alert");
        utility.switchToDefault();
        utility.closePopUp();
        Assert.assertFalse(companyBuilder.isWindowPresent(), "Window isn't closed");
        browser.refresh();
        Assert.assertTrue(projects.ProjectsList().contains(projectNameTest), "Project isn't in list");
        int projectId = projects.getProjectId(projectNameTest);
        projects.clickOnProjectLabel(projectNameTest);
        dbSteps.sendTestToDataBase(testId, RandomString.getRandomString(), RandomString.getRandomString(),
                projectId, Enum.NumberEnum.THIRTYONE.getValue(), RandomString.getRandomString());
        dbSteps.sendLogToDataBase(RandomString.getRandomLong(), JsonHelper.getValueFromJson("pathToLogFile"), testId);
        images.getScreenShot();
        dbSteps.sendAttachmentToDataBase(RandomString.getRandomLong(), images.createBlobFromPng(JsonHelper.getValueFromJson("pathToScreenShot")),
                testsData.getContentType(), testId);
        Assert.assertTrue(company.getHrefFromTestName().contains(Long.toString(testId)), "Test isn't showed up");
    }

}
