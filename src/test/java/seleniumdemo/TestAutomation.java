package seleniumdemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class TestAutomation {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Open OrangeHRM
            driver.get("https://opensource-demo.orangehrmlive.com/");

            // Login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                    .sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // PIM → Employee List
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[text()='Employee List']")
            )).click();

            // Job Title dropdown
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[text()='Job Title']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")
            ));
            dropdown.click();

            // Select "Chief Financial Officer"
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Chief Financial Officer']")
            ));
            option.click();

            // Search
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // Wait for table or no records
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-body")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No Records Found']"))
            ));

            // Get rows
            List<WebElement> rows = driver.findElements(
                    By.xpath("//div[@class='oxd-table-body']//div[@role='row']")
            );

            if (rows.size() > 0) {
                System.out.println("✅ Employees Found: " + rows.size());

                // Click first employee
                rows.get(0).click();

                // Go to Job tab
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Job']")
                )).click();

                // Verify Job Title
                WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[text()='Job Title']/following::div[1]")
                ));

                System.out.println("✅ Verified Job Title: " + jobTitle.getText());
            } else {
                System.out.println("⚠ No Records Found");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // driver.quit();
    }
}
