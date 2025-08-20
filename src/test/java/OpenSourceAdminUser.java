import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class OpenSourceAdminUser {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try{
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Thread.sleep(5000);

            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.cssSelector(".oxd-button")).click();
            Thread.sleep(5000);
            System.out.println("Login Successful");

            driver.findElement(By.xpath("//span[text()=\"Admin\"]")).click();
            Thread.sleep(3000);

            driver.findElement(By.xpath("//label[text()=\"Username\"]/../following-sibling::div/input")).sendKeys("admin");
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            Thread.sleep(5000);
            System.out.println("Search Successful");

            //List
            List<WebElement> rows = driver.findElements(By.cssSelector(".oxd-table")); // []
            boolean userFound = false; //not found
            for (WebElement row:rows){
                String userFind = row.findElement(By.xpath("(//div[@role=\"cell\"])[2]/div")).getText();
                if (userFind.equalsIgnoreCase("Admin")){
                    userFound = true;
                    break; //Dung vong lap
                }
            }
            if (userFound){
                System.out.println("User Found");
            }else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
