package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;

public class ProjectTest {
    private ILink screenShotLink = AqualityServices.getElementFactory().getLink(By.xpath("//img[@class='thumbnail']"), "ScreenShot link");

    public void clickOnScreenShot() {
        screenShotLink.clickAndWait();
        AqualityServices.getBrowser().refresh();
    }
}
