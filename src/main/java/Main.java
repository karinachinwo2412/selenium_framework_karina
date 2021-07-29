import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Main {


    public static void main(String[] args){

        //String pathToDriver = Main.class.getResource("/chromedriver").getPath(); // Mac, linux
        String pathToDriver =  Main.class.getResource("/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", pathToDriver);

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.opencart.com/index.php?route=account/login");

       /* WebElement emailElement = driver.findElement(By.name("email"));
        emailElement.sendKeys("Hola@test.com");

        WebElement passwordElement = driver.findElement(By.name("password"));
        emailElement.sendKeys("asdfq");

        driver.findElement(By.xpath("//input@type = 'submit'")).click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains@class, 'alert-danger')]"));
        Assert.assertTrue(alertMessage.isDisplayed());*/


        driver.findElement(By.name("search")).sendKeys("macbook");
        driver.findElement(By.xpath("//*[@id='search']/span/button")).click();

        driver.findElement(By.xpath("//div[contains(@class, 'caption')]/h4/a[1]")).click();
        driver.findElement(By.id("button-cart"));


        driver.close();
        driver.quit();

    }



}
