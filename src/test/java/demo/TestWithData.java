package demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWithData {
    @Test(dataProvider = "numeroProvider") // Aqui le digo donde va a jalar la data
    public void Suma(int numero){
        Assert.assertTrue(numero > 0);
        System.out.println("Numero: " + numero);
    }

    @DataProvider(name = "numeroProvider") // es el numeoro del data provider
    public Object[][] methodNumeroProvider(){
        return new Object[][]{
                {5}, {6}, {-6}
        };
    }
}
