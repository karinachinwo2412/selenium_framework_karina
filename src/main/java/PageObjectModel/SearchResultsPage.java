package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

    private static final String ERROR_MESSAGE_NO_RESULTS_DISPLAYED = "There is no product that matches the search criteria.";

    //elementos
    private By resultsSelector = By.cssSelector(".product-thumb");
    private By noResultsSelector = By.id("content");
    private By searchInput = By.name("search");
    private By searchButton = By.xpath("//div[@id='search']/span/button");
    public SearchResultsPage(WebDriver driver){
        super(driver);
    }

    public int getResultsCount(){
        return driver.findElements(resultsSelector).size();
    }

    public boolean isNoResultsVisible(){
        return driver.findElement(noResultsSelector).getAttribute("innerHTML").contains(ERROR_MESSAGE_NO_RESULTS_DISPLAYED);
    }

    public void searchProductByName(String searchCriteria){
        WebElement search = driver.findElement(searchInput);
        search.sendKeys(searchCriteria);
        driver.findElement(searchButton).click();
    }

}
