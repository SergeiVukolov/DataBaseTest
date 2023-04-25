package com.a1qa.v1task.utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.JavascriptExecutor;

public class Utility {

    public void switchToWindowHandle() {
        String parentWindowHandle = AqualityServices.getBrowser().getDriver().getWindowHandle();
        String currentWindowHandle = null;
        for (String windowHandle : AqualityServices.getBrowser().getDriver().getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                currentWindowHandle = windowHandle;
                AqualityServices.getBrowser().getDriver().switchTo().window(currentWindowHandle);
            } else AqualityServices.getBrowser().getDriver().switchTo().window(parentWindowHandle);
        }
    }

    public void switchToIframe() {
        AqualityServices.getBrowser().getDriver().switchTo().frame("info");
    }

    public void switchToDefault() {
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }

    public void closePopUp() {
        JavascriptExecutor js = AqualityServices.getBrowser().getDriver();
        js.executeScript("location.reload()");
    }

}
