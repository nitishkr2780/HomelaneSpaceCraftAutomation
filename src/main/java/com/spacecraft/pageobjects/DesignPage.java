package com.spacecraft.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class DesignPage {
    public WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public DesignPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//div[contains(text(),'Rooms')]")
    WebElement Rooms;

    @FindBy(xpath = "//span[@class='Rooms__projectName--k_LP6']")
    List<WebElement> roomListItems;

    @FindBy(id = "3DSwitch")
    WebElement switchTo3d;
    @FindBy(xpath = "//div[contains(text(),'Gallery')]")
    WebElement gallary;

    @FindBy(xpath = "//span[normalize-space()='Manual Screenshots']")
    WebElement manualScreenshots;

    @FindBy(className = "GalleryView__galleyImage--1c52R")
    List<WebElement> totalScreenshots;

    @FindBy(xpath = "//span[@id='capture']")
    WebElement capture;

    @FindBy(xpath = "//span[normalize-space()='Scene']")
    WebElement scene;


    public void clickOnRooms() {
        Rooms.click();
    }

    public void selectAnyRoomsIfPresent(String room) {
        if (!roomListItems.isEmpty()) {
            for (WebElement item : roomListItems) {
                String text = item.getText();
                if (text.equals(room)) {
                    item.click();
                    break;
                }
            }
        }
    }

    public String getSelectedRoomText() {
        WebElement roomText = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='Topbar__roomName--5NKvS']"))));
        return roomText.getText();
    }

    public void clickTo3d() {
        switchTo3d.click();
    }

    public void openGallary() {
        gallary.click();
    }

    public void clickToManualScreenshot() {
        manualScreenshots.click();
    }

    public int getTotalCountOfScreenshots() {
        return totalScreenshots.size();
    }

    public void clickToCapture() {
        capture.click();
    }

    public void clickToScene() {
        scene.click();
    }

}
