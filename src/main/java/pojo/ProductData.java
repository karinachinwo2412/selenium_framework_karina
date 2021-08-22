package pojo;

import java.util.List;

public class ProductData {
    private String product;
    private List<PriceProduct> prices;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<PriceProduct> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceProduct> prices) {
        this.prices = prices;
    }
}
