package com.a1qa.v1task.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import com.a1qa.v1task.utils.ProjectId;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class Projects {
    private ITextBox projects = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//div[@class='panel-heading']"), "Projects list");
    private ITextBox version = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//p[contains(@class,'footer')]//span"), "Version");
    private IButton addButton = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[@data-target='#addProject']"), "Add button");
    private By projectsLabelList = By.xpath("//div[@class='list-group']//a");
    ProjectId projectId = new ProjectId();

    public String getVersionNumber() {
        return version.getText();
    }

    public String getTextFromPanelHeading() {
        return projects.getText();
    }

    public ITextBox labelOfProject(String labelProject) {
        return AqualityServices.getElementFactory().getTextBox(By.xpath(String.format("//a[contains(text(),'%s')]", labelProject)), "Label of company");
    }

    public int getProjectId(String labelProject) {
        return projectId.getId(labelOfProject(labelProject).getAttribute("href"));
    }

    public void clickOnProjectLabel(String labelProject) {
        labelOfProject(labelProject).click();
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    public void clickAddButton() {
        addButton.clickAndWait();
    }

    public List<String> ProjectsList() {
        List<ITextBox> listProjects = AqualityServices.getElementFactory().findElements(projectsLabelList, ElementType.TEXTBOX);
        List<String> listProjectsText = new ArrayList<>();
        for (ITextBox project : listProjects) {
            listProjectsText.add(project.getText());
        }
        return listProjectsText;
    }

}
