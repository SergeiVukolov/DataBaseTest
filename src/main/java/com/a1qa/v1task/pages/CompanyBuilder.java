package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class CompanyBuilder {
    private ITextBox fieldInputProjectName = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@class='form-control']"), "Field input project name");
    private IButton saveProjectButton = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[@type='submit']"), "Save company button");
    private ITextBox savedCompany = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//div[contains(@class,'success')]"), "Saved company");
    private ITextBox alertMessage = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//div[contains(@class,'alert')]"), "Alert");

    public void inputProjectName(String projectName) {
        fieldInputProjectName.type(projectName);
    }

    public void saveProject() {
        saveProjectButton.click();
    }

    public String informationAboutSave() {
        return savedCompany.getText();
    }

    public String getWordFromAlert() {
        return alertMessage.getAttribute("class");
    }

    public boolean isWindowPresent() {
        return saveProjectButton.state().isDisplayed();
    }

}
