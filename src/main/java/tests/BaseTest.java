package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUpBrowser() {
        // Initialize ChromeDriver.
        driver = new ChromeDriver();

        // Maximize the browser window size.
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void closeBrowser() {
        // Terminate the browser.
        driver.quit();
    }
}
