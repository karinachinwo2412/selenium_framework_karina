package selenium;

import dataProviders.ProductProvider;
import dataProviders.SearchProvider;
import javafx.application.Application;
import javafx.stage.Stage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.PriceProduct;
import pojo.ProductData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductTest extends BaseClass {
    private String regexGetPrice = "\\d+(:?.?)\\d*";

    @Test(dataProvider = "getProductsPriceFromJson", dataProviderClass = ProductProvider.class)
    public void Test_Verify_Prices_Different_Currency(ProductData productData) {
        String searchCriteria = productData.getProduct();
        this.searchResultsPage().searchProductByName(searchCriteria);
        String name = homePage().selectFirstProductAndGetName();
        Assert.assertTrue(productPage().isTitleDisplayed(name));
        List<String> optionList =  new ArrayList<>();
        optionList.addAll(this.headerPage().getOptionsDropdownCurrency());
        for (String option : optionList) {
            this.headerPage().clickCurrency(option);
            String price = this.headerPage().getPriceProduct();
            Assert.assertTrue(this.getPriceProduct(price, productData));
        }
    }

    private boolean getPriceProduct(String price, ProductData productData) {
        double priceProduct = 0;
        String currency = "";
        // Create a Pattern object
        Pattern r = Pattern.compile(regexGetPrice);
        // Now create matcher object.
        Matcher m = r.matcher(price);
        if (m.find()) {
            priceProduct = Double.parseDouble(m.group(0));
            currency =price.replace(m.group(0), "").trim();
        }
        String finalCurrency = currency;
        double expectedPrice = productData.getPrices().stream().filter(ele -> ele.getCurrency().equals(finalCurrency))
                .findFirst()
                .map(PriceProduct::getPrice).orElse(-1.0);
        return priceProduct == expectedPrice;
    }
}
