package learninglocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class OrangeHRMLoginTest {

    public static void main(String[] args) {

        // Step 1: Launch Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 2: Open OrangeHRM website
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Step 3: Locate username field and enter value
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");

        // Step 4: Locate password field and enter value
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        // Step 5: Click Login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Step 6: Verify login success
        String expectedUrl = "dashboard";
        if (driver.getCurrentUrl().contains(expectedUrl)) {
            System.out.println(" Login Successful");
        } else {
            System.out.println(" Login Failed");
        }

        // Step 7: Close browser
        driver.quit();
    }
}
