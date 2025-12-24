package seleniumdemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class StaticTableElement {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Open website
            driver.get("https://testautomationpractice.blogspot.com/");

            // Scroll to Static Web Table
            WebElement tableHeader = driver.findElement(
                    By.xpath("//h2[text()='Static Web Table']")
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", tableHeader);

            System.out.println("✅ Scrolled to Static Web Table");

            // Locate Static Web Table
            WebElement table = driver.findElement(By.name("BookTable"));

            // Get all rows
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            System.out.println("Total Rows: " + rows.size());

            // Print table data
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                for (WebElement col : cols) {
                    System.out.print(col.getText() + " | ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // driver.quit();
    }
}
