package seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestSupportSpecialist {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1️⃣ Login
            driver.get("https://opensource-demo.orangehrmlive.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // 2️⃣ Navigate to Employee List page
            wait.until(ExpectedConditions.urlContains("/web/index.php/dashboard/index"));
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));

            // 3️⃣ Open Job Title dropdown
            WebElement jobDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='oxd-select-text-input']")));
            jobDropdown.click();

            // 4️⃣ Select "Support Specialist"
            WebElement supportOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='option']/span[text()='Support Specialist']")));
            supportOption.click();

            // 5️⃣ Click Search button
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(text(),'Search')]")));
            searchButton.click();

            // 6️⃣ Get total employees
            List<WebElement> employees = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

            System.out.println("Total employees for 'Support Specialist': " + employees.size());

        } finally {
            driver.quit();
        }
    }
}
