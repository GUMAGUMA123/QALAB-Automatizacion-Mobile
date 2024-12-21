package com.nttdata.screens;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DemosScreens extends PageObject{

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]")
    private WebElement itemProducto;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement cantidad;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement btnAgregar;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement btnAgregarCarrito;



    public void isProductsDisplay(){
        List<WebElement> productNames = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]"));
        List<WebElement> productImages = getDriver().findElements(By.xpath("//android.widget.ImageView"));
        List<WebElement> productPrices = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]"));
        if(!productNames.isEmpty() && !productImages.isEmpty() && !productPrices.isEmpty()){
            System.out.println("Mostrando productos");
        }
    }

    public void seleccionarProducto(int unidades, String producto) {

        String item = "//android.widget.ImageView[@content-desc='"+producto+"']";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        try {
            WebElement productbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
            Assert.assertTrue(productbtn.isDisplayed());
            try {
                productbtn.click();
            }catch (Exception e){
                System.out.println("Defecto detectado");
            }
        } catch (TimeoutException e) {
            Assert.fail("No se encontro el producto.");
        }
        agregarUnidades(unidades);
    }

    public void agregarUnidades(int unidades){
        for(int i=0;i<unidades;i++){
            btnAgregar.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }

    public String productoGaleria(){
        btnAgregarCarrito.click();
        waitFor(ExpectedConditions.visibilityOf(itemProducto));
        return itemProducto.getText();
    }

    public String productoCarrito(){
        return cantidad.getText();
    }
}
