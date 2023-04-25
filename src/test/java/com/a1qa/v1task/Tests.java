package com.a1qa.v1task;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.pages.Company;
import com.a1qa.v1task.pages.CompanyBuilder;
import com.a1qa.v1task.pages.ProjectTest;
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
    ProjectTest projectTest = new ProjectTest();
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);
    Logger logger = AqualityServices.getLogger();

    @Test
    public void tests() {
        String projectNameTest = RandomString.getRandomString();
        long testId = RandomString.getRandomLong();
        logger.info("Getting token");
        Assert.assertFalse(ApiResponses.getTokenResponse().isEmpty(), "Token is null");
        logger.info("Token is got");
        Authorization.sendTokenAsCookie(ApiResponses.getTokenResponse());
        browser.refresh();
        logger.info("Checking of projects page");
        Assert.assertTrue(projects.getTextFromPanelHeading().contains(testsData.getTextProjects()), "It isn't projects page");
        logger.info("Projects page is checked");
        logger.info("Checking number of version");
        Assert.assertTrue(projects.getVersionNumber().contains(testsData.getVersion().toString()), "Version is inconsistent");
        logger.info("Number of version is checked");
        projects.clickOnProjectLabel(testsData.getProjectName());
        logger.info("Checking tests by date");
        Assert.assertTrue(company.checkDates(), "Tests aren't sorted by date");
        logger.info("Tests by date are checked");
        logger.info("Checking data from DB and UI");
        Assert.assertEquals(company.getDataTable(), dbResponses.transformData(testsData.getProjectName(), company.getCountRowsFromTest()), "List from DataBase isn't included List from UI");
        logger.info("Data from DB and UI is checked");
        company.backToProjectsPage();
        projects.clickAddButton();
        utility.switchToIframe();
        companyBuilder.inputProjectName(projectNameTest);
        companyBuilder.saveProject();
        logger.info("Checking process of saving");
        Assert.assertTrue(companyBuilder.informationAboutSave().contains(testsData.getSuccess()), "Project isn't saved");
        Assert.assertTrue(companyBuilder.getWordFromAlert().contains(testsData.getWordAlert()), "It isn't message in alert");
        logger.info("Process of saving is finished successfully");
        utility.switchToDefault();
        utility.closePopUp();
        logger.info("Checking for window for saving project present");
        Assert.assertFalse(companyBuilder.isWindowPresent(), "Window isn't closed");
        logger.info("Window for saving project isn't presented");
        browser.refresh();
        logger.info("Checking companies' name for present");
        Assert.assertTrue(projects.ProjectsList().contains(projectNameTest), "Project isn't in list");
        logger.info("Companies' name is presented");
        int projectId = projects.getProjectId(projectNameTest);
        projects.clickOnProjectLabel(projectNameTest);
        dbSteps.sendTestToDataBase(testId, RandomString.getRandomString(), RandomString.getRandomString(),
                projectId, Enum.NumberEnum.THIRTYONE.getValue(), RandomString.getRandomString());
        dbSteps.sendLogToDataBase(RandomString.getRandomLong(), JsonHelper.getValueFromJson("pathToLogFile"), testId);
        images.getScreenShot(JsonHelper.getValueFromJson("pathToScreenShot"));
        dbSteps.sendAttachmentToDataBase(RandomString.getRandomLong(), images.createByteFromPng(JsonHelper.getValueFromJson("pathToScreenShot")),
                testsData.getContentType(), testId);
        logger.info("Checking for tests' present");
        Assert.assertTrue(company.getHrefFromTestName().contains(Long.toString(testId)), "Test isn't showed up");
        logger.info("Tests' present is checked");
        company.clickToTest();
        logger.info("Checking for loading log");
        Assert.assertTrue(projectTest.textFromTestTable().contains(JsonHelper.getValueFromJson("pathToLogFile")), "LogFile isn't uploaded");
        logger.info("Log is loaded");
        logger.info("Checking for loading attachment");
        Assert.assertTrue((projectTest.textFromTestTable().contains(testsData.getContentType())), "Attachment isn't uploaded");
        logger.info("Attachment is loaded");
        projectTest.clickOnScreenShot();
        images.getScreenShot(JsonHelper.getValueFromJson("pathToScreenShotTwo"));
        logger.info("checking for comparing screenshots");
        Assert.assertTrue(images.compareTwoPhoto(JsonHelper.getValueFromJson("pathToScreenShot"),
                JsonHelper.getValueFromJson("pathToScreenShotTwo")), "ScreenShots aren't similar");
        logger.info("Screenshots are similar");
    }

}
