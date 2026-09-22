package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPage extends BasePage {

    private final By title = id("productTV");
    private final By productTitles = id("titleTV");
    private final By productPrices = id("priceTV");
    private final By productImages = id("productIV");

    public CatalogPage(AndroidDriver driver) {
        super(driver);
    }

    public CatalogPage waitUntilLoaded() {
        if (!waitForText(title, "Products")) {
            throw new IllegalStateException("Catalog screen did not load");
        }
        visible(productTitles);
        return this;
    }

    public List<String> productNames() {
        return textsOf(productTitles);
    }

    public List<String> productPrices() {
        return textsOf(productPrices);
    }

    /** Opens a product card. The card image is the reliable tap target; the title text is not clickable. */
    public ProductDetailPage openProduct(int index) {
        visible(productImages);
        driver.findElements(productImages).get(index).click();
        return new ProductDetailPage(driver).waitUntilLoaded();
    }

    private List<String> textsOf(By locator) {
        visible(locator);
        return driver.findElements(locator).stream().map(WebElement::getText).toList();
    }
}
