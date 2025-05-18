package tests;

import helpers.ActionClass;
import helpers.WaitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Set;

//Test Class to book hotel using Coupon Code
public class BookHotel extends BaseTest {
    @Test
    public void bookHotel() {
        //Add the URL for the application in baseURL
        String baseUrl = "https://www.cleartrip.com/hotels";

        // Navigate to the website.
        getDriver().get(baseUrl);
        WaitClass.waitTillElementLoad("//p[.='Enter locality, landmark, city or hotel']/following::input[1]");

        //Close registration popup
        WebElement popUp = getDriver().findElement(By.xpath("//*[name()='svg' and starts-with(@class,' c-pointer c-neutral-')]"));
        popUp.click();

        //location to select
        ActionClass.actionClickAndSendKeys("//p[.='Enter locality, landmark, city or hotel']/following::input[1]", "New York");

        //Inside list, select New York from location
        WaitClass.waitTillElementLoadAndClick("(//div[@id='modify_search_list_container_id']//p[.='New York'])[1]");

        ActionClass.actionClick("//div[@tabindex=\"2\"]//div[2]");

        String monthToSelect = "April 2026";
        WaitClass.waitTillElementLoad("(//div[@class=\"DayPicker-Caption\"])[2]");
        WebElement monthName = getDriver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]"));
        String monthNameValue = monthName.getText().trim();

        //Select Check In and Check Out Date, Example = April Date - 2026
        boolean selection = false;
        while (!selection) {
            if (monthNameValue.equals(monthToSelect)) {
                //select check in as April 10 2026 and check out date April 15 2026
                ActionClass.actionClick("((//div[@class=\"DayPicker-Caption\"])[2]/following::div[.='10'])[3]");
                ActionClass.actionClick("((//div[@class=\"DayPicker-Caption\"])[2]/following::div[.='15'])[3]");
                selection = true;
            } else {
                //Click on next until we get the month
                getDriver().findElement(By.xpath("//*[name()='svg' and @data-testid=\"rightArrow\"]")).click();
            }
            if (selection) {
                break;
            }
            WaitClass.waitTillElementLoad("(//div[@class=\"DayPicker-Caption\"])[2]");
            monthName = getDriver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]"));
            monthNameValue = monthName.getText().trim();
        }

        //Click Search Hotels
        ActionClass.actionClick("//p[.='Search hotels']");
        ActionClass.actionClick("(//div[@id=\"outer-element-container\"]//following::img)[1]");

        // Get the parent window handle
        String parentWindowHandle = getDriver().getWindowHandle();

        // Get all window handles
        Set<String> allWindowHandles = getDriver().getWindowHandles();

        // Loop through all window handles
        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                // Switch to the child window
                getDriver().switchTo().window(handle);
                String secondWindow = getDriver().getWindowHandle();
                System.out.println("Switched to Child Window with Handle: " + handle);

                WebElement RoomHeader = getDriver().findElement(By.xpath("//h4[.='Room Only']"));

                // Scroll the element into view using JavascriptExecutor
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                js.executeScript("arguments[0].scrollIntoView(true);", RoomHeader);

                ActionClass.actionClick("(//h4[.='Room Only']//following::h4[.='Book'])[1]");

                ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
                getDriver().switchTo().window(tabs.get(2));

                //Add coupon and click apply
                WebElement couponHeader = getDriver().findElement(By.xpath("//h2[.='Apply coupon or gift card']"));
                JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", couponHeader);
                WebElement coupon = getDriver().findElement(By.xpath("//input[@id=\"Coupon code\"]"));
                coupon.sendKeys("SUMMER25");
                WebElement couponApplyButton = getDriver().findElement(By.xpath("//input[@id=\"Coupon code\"]//following::h4[.='Apply']"));
                couponApplyButton.click();

                //After entering valid coupon, use Assert to validate amount then we can go to proceed to checkout.....
                System.out.println("<<<< Price can be Validated here after coupon code is applied >>>>");
            }
        }
    }
}