package com.spacecraft.testcases;

import com.spacecraft.base.BaseClass;
import com.spacecraft.pageobjects.IndexPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IndexPageTest extends BaseClass {
    @BeforeMethod
    public void setup(){
        launchApp();
    }

    @Test
    public void LaunchSpaceCraftDesignApp()  {
        driver.get((properties.getProperty("url")));
        IndexPage indexPage= new IndexPage(driver);
        indexPage.clickSkipToScene();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
