package com.spacecraft.base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    public Properties properties;
    public WebDriver driver;

    @BeforeMethod
    public void loadConfig() {
        try {
            FileReader file = new FileReader(".//Configuration/Config.properties");
            properties = new Properties();
            properties.load(file);

            System.out.println("Configuration file  Loaded Successfully");

        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }
    }

    public void launchApp() {
        switch (properties.getProperty("browser").toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println(" No browser found");
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
}
