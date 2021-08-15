import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestAccount {

    @Description("Validate test login was successful")
    @Test
    public void Test_Login_Successful(){
        String username = "karina.chinwo.camacho@ucreativa.com";
        String password = "1234";

        //es otra forma de llamar al ejecutable, que yo quiero, en mac, no es necesario poner el .exe
        //String pathToDriver =  Test.class.getResource("/chromedriver.exe").getPath();
        //System.setProperty("webdriver.chrome.driver", pathToDriver);

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Go To Login Page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();

        /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());

        TakeScreenshot(driver);

        driver.close();
        driver.quit();
    }

    @Description("Validate test login is working with non valid credentials")
    @Test
    public void Test_Login_Unsuccessful(){
        String username = "karina.chinwo.camacho@ucreativa.com";
        String password = "123477777";
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";

        //es otra forma de llamar al ejecutable, que yo quiero, en mac, no es necesario poner el .exe
        //String pathToDriver =  Test.class.getResource("/chromedriver.exe").getPath();
        //System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Go To Login Page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();

        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());

        TakeScreenshot(driver);

        driver.close();
        driver.quit();
    }

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] TakeScreenshot(WebDriver driver){
        // tomar un screenshot
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
