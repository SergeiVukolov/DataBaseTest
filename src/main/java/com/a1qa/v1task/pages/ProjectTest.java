package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import com.a1qa.v1task.utils.Utility;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class ProjectTest {
    private ILink screenShotLink = AqualityServices.getElementFactory().getLink(By.xpath("//a[contains(@href,'data')]"), "ScreenShot link");
    private By tableInTest = By.xpath("//table[@class='table']//td");
    Utility utility = new Utility();

    public void clickOnScreenShot() {
        screenShotLink.click();
        utility.switchToWindowHandle();
        AqualityServices.getBrowser().refresh();
    }

    public List<String> textFromTestTable() {
        List<ITextBox> informationFromTestTable = AqualityServices.getElementFactory().findElements(tableInTest, ElementType.TEXTBOX);
        List<String> informationFromTable = new ArrayList<>();
        for (ITextBox inf : informationFromTestTable) {
            informationFromTable.add(inf.getText());
        }
        return informationFromTable;
    }

}
