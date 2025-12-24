package seleniumdemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TestAutomationTester {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("https://opensource-demo.orangehrmlive.com/");

            // 🔐 Login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                    .sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // 📂 PIM → Employee List
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[text()='Employee List']")
            )).click();

            // 🔽 Job Title dropdown
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='Job Title']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")
            ));
            dropdown.click();

            // 🎯 Select Automation Tester
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Automation Tester'] | //span[text()='Automaton Tester']")
            ));
            option.click();

            // 🔍 Click Search
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // ✅ Proper validation (stable)
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[text()='No Records Found']")
                    ),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("oxd-table-body")
                    )
            ));

            // Check result
            boolean noRecords = driver.findElements(
                    By.xpath("//span[text()='No Records Found']")
            ).size() > 0;

            if (noRecords) {
                System.out.println("✅ Automation Tester search successful");
                System.out.println("⚠ Result: No Records Found (Expected for demo site)");
            } else {
                System.out.println("⚠ Records exist (unexpected for demo data)");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // driver.quit();
    }
}
