package com.spacecraft.testcases;

import com.spacecraft.base.BaseClass;
import com.spacecraft.pageobjects.DesignPage;
import com.spacecraft.pageobjects.IndexPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class IndexPageTest extends BaseClass {
    WebDriverWait wait;
    DesignPage designPage;

    @BeforeMethod
    public void setup() {
        launchApp();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void LaunchSpaceCraftAndVerifyScreenshotTaken() throws InterruptedException {

        driver.get((properties.getProperty("url")));
        IndexPage indexPage = new IndexPage(driver);
        designPage = indexPage.clickSkipToScene();
        designPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
        designPage.selectAnyRoomsIfPresent("Livingroom1");
        String selectedRoomText = designPage.getSelectedRoomText();
        if (selectedRoomText.contains("Livingroom1")) {
            designPage.clickTo3d();
        }
        Thread.sleep(5000); //wait to  load 3-d fully
        designPage.openGallary();
        designPage.clickToManualScreenshot();
        int noOfScreenshotBefore = designPage.getTotalCountOfScreenshots();
        System.out.println("total screenshot before :  " + noOfScreenshotBefore);
        designPage.clickToCapture();
        designPage.clickToScene();
        Thread.sleep(5000); //wait for screenshot available
        int noOfScreenshotAfter = designPage.getTotalCountOfScreenshots();
        System.out.println("total screenshot after :  " + noOfScreenshotAfter);
        Assert.assertEquals(noOfScreenshotBefore + 1, noOfScreenshotAfter, "No screenshot captured");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
