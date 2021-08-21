package selenium;

import PageObjectModel.HeaderPage;
import PageObjectModel.LoginPage;
import PageObjectModel.RegisterPage;
import PageObjectModel.Utils;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;


public class TestAccount extends BaseClass {

    @Description("Validate test login was successful")
    @Test(description = "Test Login Success")
    public void Test_Login_Successful(){
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String username = "karina.chinwo.camacho@ucreativa.com";
        String password = "1234";

        //Go To Login Page
        headerPage.clickOnMyAccount();
        headerPage.clickOnLoginButton();

        //Llenar formulario
        loginPage.EnterEmail(username);
        loginPage.EnterPassword(password);
        loginPage.ClickSubmitButton();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Description("Validate test login is working with non valid credentials")
    @Test(description = "Test Login Not Success")
    public void Test_Login_Unsuccessful(){
        /*Test Driven Development
         * AS A USER I want to be to able to login properly
         *
         * GIVEN I am at login page
         * AND I log in with juan.piedra@ucreativa and asdf
         * WHEN when user is at dashboard page
         * THEN logout button is displayed
         * */
        LoginPage loginPage = new LoginPage(driver);
        String username = "karina.chinwo.camacho@ucreativa.com";
        String password = "123477777";
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";

        //Go To Login Page
        loginPage.GoTo();
        loginPage.login(username, password);

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }

    @Test
    public void Test_Create_New_Account(){
        //SETUP
        String firstName = "Karina";
        String lastName = "Chin Wo";
        String email = Utils.generateRandomEmail();
        String telephone = "88998899";
        String password = "1234";
        String expectedMessage = "Your Account Has Been Created!";
        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetConfirmationMessage(), expectedMessage);
    }

    @Test
    public void Test_Duplicate_Email(){
        //SETUP
        String firstName = "Karina";
        String lastName = "Chin Wo";
        String email = "kari1234@chinwo.com";
        String telephone = "88998899";
        String password = "1234";
        String expectedMessage = "Warning: E-Mail Address is already registered!";
        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetEmailDuplicateMessage().trim(), expectedMessage.trim());
    }


}
