package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;

//Wait helper class
public class WaitClass extends BaseTest {

    //Wait till element loads
    public static void waitTillElementLoad(String locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    //Wait till element loads and then click
    public static void waitTillElementLoadAndClick(String locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        WebElement element = getDriver().findElement(By.xpath(locator));
        element.click();
    }
}

