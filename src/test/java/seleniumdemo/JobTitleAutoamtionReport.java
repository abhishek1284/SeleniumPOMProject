package seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobTitleAutoamtionReport {

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

            // 2️⃣ Navigate to Employee List
            wait.until(ExpectedConditions.urlContains("/web/index.php/dashboard/index"));
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));

            // 3️⃣ Open Job Title dropdown
            WebElement jobDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                    "//label[text()='Job Title']/../following-sibling::div//div[@class='oxd-select-text-input']")));
            jobDropdown.click();

            // 4️⃣ Get all Job Titles
            List<WebElement> jobOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[@role='option']/span")));

            System.out.println("Total Job Titles found: " + jobOptions.size());

            // 5️⃣ Check duplicates
            Set<String> uniqueTitles = new HashSet<>();
            for (WebElement option : jobOptions) {
                String text = option.getText().trim();
                if(!text.equals("-- Select --")) {
                    uniqueTitles.add(text);
                }
            }
            if(uniqueTitles.size() == jobOptions.size() - 1) {
                System.out.println("All Job Titles are unique ✅");
            } else {
                System.out.println("Duplicate Job Titles found ❌");
            }

            // 6️⃣ Loop through each Job Title
            for (int i = 0; i < jobOptions.size(); i++) {
                // Reopen dropdown
                jobDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//label[text()='Job Title']/../following-sibling::div//div[@class='oxd-select-text-input']")));
                jobDropdown.click();

                // Refresh job options list
                jobOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[@role='option']/span")));

                WebElement option = jobOptions.get(i);
                String jobTitle = option.getText().trim();

                if(jobTitle.equals("-- Select --")) continue; // skip placeholder

                // Select Job Title
                option.click();

                // Click Search button
                WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//button[@type='submit' and contains(text(),'Search')]")));
                searchButton.click();

                // Wait for Employee List to update
                List<WebElement> employees = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

                // Print report
                if (employees.size() > 0) {
                    System.out.println(jobTitle + " → Employees: " + employees.size() + " ✅");
                } else {
                    System.out.println(jobTitle + " → Employees: 0 ❌");
                }

                // Optional: keep screen visible 1-2 seconds
                Thread.sleep(1500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
