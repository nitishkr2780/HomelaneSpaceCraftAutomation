package com.spacecraft.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IndexPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//p[@class='HeaderComponent-module__design_button--qW3DU']")
    WebElement skipToSceneLocator;

    public FloorPlannerPage clickSkipToScene() {
        wait.until(ExpectedConditions.visibilityOf(skipToSceneLocator));
        skipToSceneLocator.click();
        return new FloorPlannerPage(driver);
    }
}
