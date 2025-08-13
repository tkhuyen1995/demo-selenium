import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenSource {
    public static void main(String[] args) {
        // yêu cầu: viết chương trình automation tìm đến trang opensource và lấy ra ô username, password và button login

        //setup driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try{
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            //wait thay thế bằng thread.sleep -> bọc vô bằng try catch
            //simple way (sau này sẽ thay thế bằng Webdriverwait
            Thread.sleep(10000);

            //locator
            WebElement userName = driver.findElement(By.xpath("//input[@name=\"username\"]"));
            WebElement passWord = driver.findElement(By.xpath("//input[@name=\"password\"]"));

            String userNameText = driver.findElement(By.xpath("//p[starts-with(normalize-space(),\"Username\")]")).getText();
            String passWordText = driver.findElement(By.xpath("//p[starts-with(normalize-space(),\"Password\")]")).getText();

            String userNameValue = userNameText.split(":")[1].trim();
            String passWordValue = passWordText.split(":")[1].trim();

            //nhập liệu
            userName.sendKeys(userNameValue);
            passWord.sendKeys(passWordValue);

            WebElement btnLogin = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
            btnLogin.click();
            Thread.sleep(10000);

            System.out.println("Đăng nhập thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
