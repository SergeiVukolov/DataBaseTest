package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class Projects {

    private ITextBox version = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//p[contains(@class,'footer')]//span"), "Version");

    public String getVersionNumber() {
        return version.getText();
    }

}
