package iframeexample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragAndDrop {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        // Scroll to Drag and Drop section
        WebElement dragDropHeader = driver.findElement(
                By.xpath("//h2[text()='Drag and Drop']")
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dragDropHeader);

        Thread.sleep(2000);

        // Locate draggable and droppable using XPath
        WebElement source = driver.findElement(
                By.xpath("//div[@id='draggable']")
        );

        WebElement target = driver.findElement(
                By.xpath("//div[@id='droppable']")
        );

        // Perform drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        Thread.sleep(2000);

        // driver.quit();
    }
}
