package seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class JobTitleDropdownScroll {

    public static void main(String[] args) throws InterruptedException {
        // 1️⃣ Setup ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // 2️⃣ Go to OrangeHRM login page
            driver.get("https://opensource-demo.orangehrmlive.com/");

            // 3️⃣ Login to demo site
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // 4️⃣ Navigate to Employee List page (PIM module)
            Thread.sleep(3000); // wait for dashboard to load
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
            Thread.sleep(3000); // wait for Employee List page to load

            // 5️⃣ Click Job Title dropdown to open it
            WebElement jobTitleDropdown = driver.findElement(By.xpath(
                    "//label[text()='Job Title']/../following-sibling::div//div[@class='oxd-select-text-input']"));
            jobTitleDropdown.click();
            Thread.sleep(1000);

            // 6️⃣ Get all options in the dropdown
            List<WebElement> jobOptions = driver.findElements(By.xpath("//div[@role='option']/span"));

            // 7️⃣ Scroll through and print all Job Titles
            System.out.println("Job Titles available in dropdown:");
            for (WebElement option : jobOptions) {
                System.out.println(option.getText());
            }

        } finally {
            // 8️⃣ Close browser
            driver.quit();
        }
    }
}
