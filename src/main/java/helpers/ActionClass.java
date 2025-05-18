package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;

//Action Class Helper
public class ActionClass extends BaseTest {

    //To click element using actions
    public static void actionClick(String locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        Actions actions = new Actions(getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        WebElement location = getDriver().findElement(By.xpath(locator));
        actions.moveToElement(location).click().perform();
    }

    //To click element using actions and send value
    public static void actionClickAndSendKeys(String locator, String value) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        Actions actions = new Actions(getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        WebElement location = getDriver().findElement(By.xpath(locator));
        actions.moveToElement(location).click().sendKeys(value).perform();
    }
}
