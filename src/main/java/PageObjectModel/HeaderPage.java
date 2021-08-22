package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pojo.PriceProduct;
import pojo.ProductData;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HeaderPage extends BasePage {

    //Elementos
    private By myAccountLinkLocator = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]");
    private By loginButtonLocator = By.linkText("Login");
    private By registerButtonLocator = By.linkText("Register");
    private By shoppingCartLocator = By.linkText("Shopping Cart");
    private By yourStoreButtonLocator = By.linkText("Your Store");
    private By currencyDropdown = By.xpath("//*[@id=\"form-currency\"]/div/button");
    private By getCurrencyDropdownList = By.xpath("//*[@id=\"form-currency\"]/div/ul/li");
    private By getPriceProduct = By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[1]/h2");



    public HeaderPage(WebDriver _driver) {
        super(_driver);
    }

    public void clickOnMyAccount() {
        driver.findElement(myAccountLinkLocator).click();
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    public void clickOnRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    public void clickOnCartButton() {
        driver.findElement(shoppingCartLocator).click();
    }

    public void clickOnYourStoreButton() {
        driver.findElement(yourStoreButtonLocator).click();
    }

    public List<String> getOptionsDropdownCurrency() {
        driver.findElement(currencyDropdown).click();
        return driver.findElements(getCurrencyDropdownList).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void checkOptionDropdownCurrency(WebElement currencyOption) {
       currencyOption.click();
    }

    public void clickCurrency(String option){
        if(!driver.findElement(getCurrencyDropdownList).isDisplayed())
            driver.findElement(currencyDropdown).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/ul/li/button[contains(.,\"" + option +"\")]"));
        element.click();
    }

    public String getPriceProduct(){
        return driver.findElement(getPriceProduct).getText().replace(",", "");

    }


}
