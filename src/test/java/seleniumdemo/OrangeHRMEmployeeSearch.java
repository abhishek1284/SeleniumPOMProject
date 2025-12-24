package seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrangeHRMEmployeeSearch {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://opensource-demo.orangehrmlive.com/");

        // --- LOGIN ---
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // --- NAVIGATE TO PIM → EMPLOYEE LIST ---
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Employee List']"))).click();

        // --- CLICK JOB TITLE DROPDOWN ---
        WebElement jobDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-select-text-input' and @tabindex='0']")));
        js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", jobDropdown);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", jobDropdown);

        // --- SCROLL AND SELECT OPTION ---
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@role='option']")));

        String desiredOption = "Automation Tester";
        boolean found = false;

        for (WebElement option : options) {
            js.executeScript("arguments[0].scrollIntoView(true);", option);
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(desiredOption)) {
                js.executeScript("arguments[0].click();", option);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Option '" + desiredOption + "' not found in dropdown.");
            driver.quit();
            return;
        }

        // --- CLICK SEARCH BUTTON USING YOUR XPATH ---
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
        js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", searchBtn);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", searchBtn);

        // --- GET EMPLOYEES AND COUNT ---
        List<WebElement> employees = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='oxd-table-body']/div")));

        System.out.println("Total employees found: " + employees.size());

        for (WebElement emp : employees) {
            String empName = emp.findElement(By.xpath(".//div[3]")).getText();
            String empJobTitle = emp.findElement(By.xpath(".//div[4]")).getText();
            System.out.println("Employee: " + empName + " | Job Title: " + empJobTitle);
        }

        driver.quit();
    }
}
