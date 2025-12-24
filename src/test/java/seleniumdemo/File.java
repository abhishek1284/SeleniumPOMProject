package seleniumdemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class File {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Open OrangeHRM
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Click PIM
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();

        // Click Employee List (FIXED)
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'oxd-topbar-body-nav-tab-item') and text()='Employee List']")
        )).click();

        // Click Job Title dropdown (FIXED)
        WebElement jobTitleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Job Title']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")
        ));
        jobTitleDropdown.click();

        // Select Automaton Tester (with scroll)
        WebElement automatonTester = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option']//span[text()='Automaton Tester']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", automatonTester);
        automatonTester.click();

        // Click Search
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and .=' Search ']")
        )).click();

        System.out.println("✅ Search completed successfully");
    }
}
