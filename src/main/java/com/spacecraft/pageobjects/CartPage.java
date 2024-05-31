package com.spacecraft.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    public WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @FindBy(className = "RoomSummary__productName--2qEeu")
    List<WebElement> productListLocator;

    By furnitureTypeDrpLocator = By.className("RoomSummary__roomSummarySelect--2zRHB");
    By roomElementsListLocator = By.className("RoomSummary__roomName--1MEL9");
    By fittedFurnitureTypeListsLocator = By.cssSelector(".RoomSummary__zoneTitle--e3gdK");
    By fittedFurnitureTypeItemsNameLocator = By.cssSelector(".RoomSummary__zoneSubTitle--e385p");

    public void clickonRoomInCart(String room) {
        List<WebElement> roomElements = driver.findElements(roomElementsListLocator);
        for (WebElement roomElement : roomElements) {
            if (roomElement.getText().equals(room)) {
                roomElement.click();
                break;
            }
        }
    }

    public void getAllItemsFromCart() {
        List<WebElement> titles = driver.findElements(fittedFurnitureTypeListsLocator);
        List<WebElement> subtitles = driver.findElements(fittedFurnitureTypeItemsNameLocator);
        if (titles.size() == subtitles.size()) {
            for (int i = 0; i < titles.size(); i++) {
                //   String titleText = titles.get(i).getText();
                String subtitleText = subtitles.get(i).getText();
                // System.out.println("Title: " + titleText);
                System.out.println("Items name in cart: " + subtitleText);
            }
        } else {
            System.out.println("Mismatch in number of titles and subtitles");
        }
    }

    public void selectTypeOfFurnitureInCart(String furnitureType) {
        WebElement dropdown = driver.findElement(furnitureTypeDrpLocator);
        Select select = new Select(dropdown);
        select.selectByValue(furnitureType);
    }

    public void getAllItemsPresent(String FurnitureType) {
        for (WebElement productElement : productListLocator) {
            String productName = productElement.getText();
            System.out.println(FurnitureType + " present inside cart :" + productName);
        }
    }
}
