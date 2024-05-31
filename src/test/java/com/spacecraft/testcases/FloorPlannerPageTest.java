package com.spacecraft.testcases;

import com.spacecraft.base.BaseClass;
import com.spacecraft.pageobjects.CartPage;
import com.spacecraft.pageobjects.FloorPlannerPage;
import com.spacecraft.pageobjects.IndexPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.time.Duration;

public class FloorPlannerPageTest extends BaseClass {
    WebDriverWait wait;
    FloorPlannerPage floorPlannerPage;
    CartPage cartpage;
    static final String ROOM_BEDROOM = "Bedroom";
    private static final String SERVICE_COUNTER_TOP = "Counter top material";
    private static final String SERVICE_SUBTYPE_GRANITE = "Granite Regular";
    private static final String FURNITURE_LOOSE = "looseFurniture";
    private static final String FURNITURE_SERVICES = "services";


    @BeforeClass
    public void initialSetup(){
        launchApp();
        driver.get((properties.getProperty("url")));
        IndexPage indexPage = new IndexPage(driver);
        floorPlannerPage = indexPage.clickSkipToScene();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void setup() {
        driver.get("https://spacecraft-dev-4.homelane.com/v/floorplanner/909b3e25-124d-470a-9cd7-be8d44265a4a");
    }
    @Test(priority = 1)
    public void takeScreenshotAndValidateInGallary() throws InterruptedException {
        floorPlannerPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            floorPlannerPage.clickTo3d();
        }
        Thread.sleep(5000); //wait to  load 3-d fully
        floorPlannerPage.openGallary();
        floorPlannerPage.clickToManualScreenshot();
        int noOfScreenshotBefore = floorPlannerPage.getTotalCountOfScreenshots();
        System.out.println("total screenshot before :  " + noOfScreenshotBefore);
        floorPlannerPage.clickToCapture();
        floorPlannerPage.clickToScene();
        Thread.sleep(5000); //wait for screenshot to available
        int noOfScreenshotAfter = floorPlannerPage.getTotalCountOfScreenshots();
        System.out.println("total screenshot after : " + noOfScreenshotAfter);
        Assert.assertEquals(noOfScreenshotBefore + 1, noOfScreenshotAfter, "No screenshot captured");
    }

    @Test(priority = 2)
    public void addFittedFurnitureToAnyRoomAndValidateInCart() throws InterruptedException {
        floorPlannerPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        floorPlannerPage.clickRoomCrossButton();
        Thread.sleep(5000);
        floorPlannerPage.clickOnFurniture();
        floorPlannerPage.clickOnFittedFurniture();
        floorPlannerPage.clickOnStorageItemInFittedFurniture();
        floorPlannerPage.clickOnStudyUnitInStorage();
        floorPlannerPage.selectAnyStudyUnit(1);
        Thread.sleep(5000); // wait to successfully adding  into cart
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            cartpage = floorPlannerPage.clickOnaddedCartOption();
        }
        cartpage.clickonRoomInCart(ROOM_BEDROOM);
        cartpage.getAllItemsFromCart();
    }

    @Test(priority = 3)
    public void addLooseFurnitureToAnyRoomAndValidateInCart() throws InterruptedException {
        floorPlannerPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        floorPlannerPage.clickRoomCrossButton();
        Thread.sleep(5000);
        floorPlannerPage.clickOnFurniture();
        floorPlannerPage.clickOnLooseFurniture();
        floorPlannerPage.clickOnBedThenAllBedTypes();
        floorPlannerPage.selectFirstBedUnitAndAddToCart();
        Thread.sleep(5000); // wait to successfully adding  into cart
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            cartpage = floorPlannerPage.clickOnaddedCartOption();
        }
        cartpage.clickonRoomInCart(ROOM_BEDROOM);
        cartpage.selectTypeOfFurnitureInCart(FURNITURE_LOOSE);
        cartpage.getAllItemsPresent(FURNITURE_LOOSE);
    }
    @Test(priority = 4)
    public void addServiceToAnyRoomAndVerifyInCart() throws InterruptedException {
        floorPlannerPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        floorPlannerPage.clickRoomCrossButton();
        Thread.sleep(5000);
        floorPlannerPage.clickOnServices();
        floorPlannerPage.clickServicesByText(SERVICE_COUNTER_TOP);
        floorPlannerPage.selectSubService(SERVICE_SUBTYPE_GRANITE);
        floorPlannerPage.selectFirstServiceItem();
        Thread.sleep(5000);
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            cartpage = floorPlannerPage.clickOnaddedCartOption();
        }
        cartpage.clickonRoomInCart(ROOM_BEDROOM);
        cartpage.selectTypeOfFurnitureInCart(FURNITURE_SERVICES);
        cartpage.getAllItemsPresent(FURNITURE_SERVICES);
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
