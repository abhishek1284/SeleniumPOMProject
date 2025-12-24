package iframeexample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class ClickDouble {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open the webpage
        driver.get("https://testautomationpractice.blogspot.com/");

        // Scroll to the <h2> heading "Double Click"
        WebElement heading = driver.findElement(By.xpath("//h2[text()='Double Click']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);

        // Optional: highlight heading
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", heading);

        // Wait 3 seconds to see heading
        Thread.sleep(3000);

        // Locate the "Copy Text" button
        WebElement copyButton = driver.findElement(By.xpath("//button[@ondblclick='myFunction1()']"));

        // Scroll button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copyButton);

        // Wait 2 seconds to see scroll effect
        Thread.sleep(2000);

        // Double click the button
        Actions actions = new Actions(driver);
        actions.doubleClick(copyButton).perform();

        // Wait 15 seconds to see result (total screen time ~20 sec)
        Thread.sleep(15000);

        // Close browser
        driver.quit();
    }
}
