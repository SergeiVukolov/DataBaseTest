package com.a1qa.v1task.steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.pages.CompanyPage;
import com.a1qa.v1task.responses.DbResponses;
import com.a1qa.v1task.utils.JsonHelper;
import org.testng.Assert;

public class VariantOneUiDbSteps {
    private final Logger logger = AqualityServices.getLogger();
    private final CompanyPage companyPage = new CompanyPage();
    private final DbResponses dbResponses = new DbResponses();
    private final VariantOneDbSteps variantOneDbSteps = new VariantOneDbSteps();
    private final TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    public void assertTestsFromDbAndUi() {
        logger.info("Checking data from DB and UI");
        Assert.assertEquals(companyPage.getDataTable(), dbResponses.transformData(variantOneDbSteps.getArrayListWithTests
                        (testsData.getProjectName(), companyPage.getCountRowsFromTest())),"List from DataBase isn't included List from UI");
        logger.info("Data from DB and UI is checked");
    }

}
