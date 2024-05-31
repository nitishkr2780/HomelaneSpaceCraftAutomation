package com.spacecraft.base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public Properties properties;
    public WebDriver driver;

    @BeforeClass
    public void loadConfig() {
        try {
            FileReader file = new FileReader(".//Configuration/Config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }
    }
    public void launchApp() {
        switch (properties.getProperty("browser").toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--user-data-dir=/home/nitish/.config/google-chrome/Default"); //for store  session
                driver = new ChromeDriver(options);
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
