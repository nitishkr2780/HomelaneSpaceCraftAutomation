package com.spacecraft.testcases;

import com.spacecraft.base.BaseClass;
import com.spacecraft.pageobjects.DesignerPage;
import com.spacecraft.pageobjects.FloorPlannerPage;
import com.spacecraft.pageobjects.IndexPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.spacecraft.testcases.FloorPlannerPageTest.ROOM_BEDROOM;

public class DesignerPageTest extends BaseClass {
    WebDriverWait wait;
    FloorPlannerPage floorPlannerPage;
    Actions actions;
    DesignerPage designerPage;
    String userChoice = "Material"; //("Product", "Material", "Modules", "Handles", "Advanced")
    String materialType = "Carcass"; //"Carcass," "Shutter," "Visible Sides," or "Skirting"
    String moduleName = "Loft side panel left"; //Loft side panel left  //Loft frame and shutter-1s

    String materialTypeOption = "Prelam BWR Ply"; //Laminate Solid Hi Gloss on Hydrogaurd Plus HDF,Prelam BWR Ply

    String colorOption = "Frosty White";//Autumn leaf,Frosty White

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchApp();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get((properties.getProperty("url")));
        IndexPage indexPage = new IndexPage(driver);
        floorPlannerPage = indexPage.clickSkipToScene();
        floorPlannerPage.clickOnRooms();
        Thread.sleep(5000); //wait to load  room fully
    }

    @Test(priority = 1)
    public void editMaterialPresentInsideRoom() throws InterruptedException {
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            floorPlannerPage.clickTo3d();
        }
        designerPage = new DesignerPage(driver);
        Thread.sleep(5000);
        designerPage.clickOnCameraView();
        designerPage.selectCameraView("wall a");
        designerPage.clickOnAnyItemPresentInsideRoomInCanvas();
        designerPage.clickOnEdit();
        designerPage.selectAnyProductTabByChoice(userChoice);
        designerPage.clickMaterialTypeByChoice(materialType);
        designerPage.selectModuleCheckbox(""); //for Skirting , it is not available
        designerPage.selectMaterialTypeOptionByVisibleText("");
        designerPage.clickHereButtonForColor();
        designerPage.clickOnColorOptionByChoice(colorOption);
    }

    @Test(priority = 2)
    public void editModulesPresentInsideRoom() throws InterruptedException {
        floorPlannerPage.selectAnyRoomsIfPresent(ROOM_BEDROOM);
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains(ROOM_BEDROOM)) {
            floorPlannerPage.clickTo3d();
        }
        designerPage = new DesignerPage(driver);
        Thread.sleep(5000);
        designerPage.clickOnCameraView();
        designerPage.selectCameraView("wall a");
        designerPage.clickOnAnyItemPresentInsideRoomInCanvas();
        designerPage.clickOnEdit();
        designerPage.selectAnyProductTabByChoice("Modules");
        Thread.sleep(5000); //wait to load modules fully
        designerPage.clickOnAddModuleDropDown();
        designerPage.selectModuleFromDropDown();
        designerPage.selectModule("");
        designerPage.clickOnAddToSpace();
        designerPage.clickOnSaveAndApplyButton();
    }

    @Test(priority = 3)
    public void validationOf2dImageGeneration() throws InterruptedException {
        floorPlannerPage.selectAnyRoomsIfPresent("Kitchen");
        String selectedRoomText = floorPlannerPage.getSelectedRoomFromCartLocation();
        if (selectedRoomText.contains("Kitchen")) {
            floorPlannerPage.clickTo3d();
        }
        designerPage = new DesignerPage(driver);
        Thread.sleep(5000); // To  load 3d completely
        if (designerPage.checkPlacementStatusAvailability()) {
            designerPage.clickGenerate2DImage();
        }
        Thread.sleep(5000);
        designerPage.check2DGenerationMessage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
