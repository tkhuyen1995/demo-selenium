import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenSourceAddEmployee {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try{
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Thread.sleep(5000);

            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            System.out.println("Đăng nhập thành công");
            Thread.sleep(5000);

            driver.findElement(By.xpath("//span[text()=\"PIM\"]")).click();
            System.out.println("Click PIM thành công");
            Thread.sleep(5000);

            driver.findElement(By.xpath("//a[contains(text(),\"Add Employee\")]")).click();
            Thread.sleep(5000);

            String firstName = "Khuyen";
            String className = "Test";
            driver.findElement(By.name("firstName")).sendKeys(firstName);
            driver.findElement(By.name("lastName")).sendKeys(className);

            WebElement empID = driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/../following-sibling::div/input"));
            String empIDValue = empID.getAttribute("value").trim();
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            System.out.println("Tạo Thành công");
            Thread.sleep(7000);

            String actualFirstName = driver.findElement(By.name("firstName")).getAttribute("value").trim();
            String actualClassName = driver.findElement(By.name("lastName")).getAttribute("value").trim();
            String actualEmpId = driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/../following-sibling::div/input")).getAttribute("value").trim();

            boolean isNameCorrect = firstName.equals(actualFirstName) && className.equals(actualClassName);
            boolean isIdCorrect = empIDValue.equals(actualEmpId);

            if(isIdCorrect && isNameCorrect){
                System.out.println("Thêm nhân viên thành công và thông tin trùng khớp");
            }else {
                System.out.println("thông tin khi lưu không trùng khớp");
            }

            Thread.sleep(7000);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
