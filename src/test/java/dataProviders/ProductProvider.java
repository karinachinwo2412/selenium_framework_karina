package dataProviders;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import pojo.ProductData;
import pojo.SearchData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ProductProvider {
    @DataProvider(name = "getProductsPriceFromJson")
    private Object[][] getSearchDataFromJson() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/testData/products.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<ProductData> testData = new Gson().fromJson(dataSet, new TypeToken<List<ProductData>>() {}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
