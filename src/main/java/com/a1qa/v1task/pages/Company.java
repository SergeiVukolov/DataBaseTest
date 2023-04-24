package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import com.a1qa.v1task.models.CompanyTests;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.utils.Enum;
import com.a1qa.v1task.utils.JsonHelper;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private By tableTests = By.xpath("//table[contains(@class,'table')]//tr");
    private By tableColumns = By.xpath(".//td");
    private IButton homeButton = AqualityServices.getElementFactory().getButton(By.xpath("//a[contains(text(),'Home')]"), "Home button");
    private ILink testLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(@href,'testInfo')]"), "Test link");
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    public List<CompanyTests> getDataTable() {
        List<ITextBox> tableRows = AqualityServices.getElementFactory().findElements(tableTests, ElementType.TEXTBOX);
        int size = tableRows.size();
        List<CompanyTests> dataTable = new ArrayList<>(tableRows.size());
        int row = 1;
        while (dataTable.size() < size - 1) {
            List<ITextBox> columns = tableRows.get(row).findChildElements(tableColumns, ElementType.TEXTBOX);
            CompanyTests rowData = new CompanyTests();
            for (int i = 0; i < columns.size() - 1; i++) {
                rowData.setName(columns.get(Enum.NumberEnum.ZERO.getValue()).getText());
                rowData.setMethod(columns.get(Enum.NumberEnum.ONE.getValue()).getText());
                rowData.setStatus(columns.get(Enum.NumberEnum.TWO.getValue()).getText());
                rowData.setStartTime(columns.get(Enum.NumberEnum.THREE.getValue()).getText());
                rowData.setEndTime(columns.get(Enum.NumberEnum.FOUR.getValue()).getText());
                rowData.setDuration(columns.get(Enum.NumberEnum.FIVE.getValue()).getText());
            }
            dataTable.add(rowData);
            row++;
        }
        return dataTable;
    }

    public int getCountRowsFromTest() {
        return getDataTable().size();
    }

    public boolean checkDates() {
        List<String> listOfStartDates = new ArrayList<>();
        for (CompanyTests dates : getDataTable()) {
            listOfStartDates.add(dates.getStartTime());
        }
        for (int i = 0; i < listOfStartDates.size() - 1; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(testsData.getDatePattern());
            try {
                if (dateFormat.parse(listOfStartDates.get(i)).compareTo(dateFormat.parse(listOfStartDates.get(i + 1))) > 0)  {
                    continue;
                } else {
                    return false;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } return true;
    }

    public void backToProjectsPage() {
        homeButton.clickAndWait();
    }

    public String getHrefFromTestName() {
        return testLink.getHref();
    }

}
