package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPage extends BasePage {

    private final By title = id("productTV");
    private final By productTitles = id("titleTV");

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
        visible(productTitles);
        return driver.findElements(productTitles).stream().map(WebElement::getText).toList();
    }

    public ProductDetailPage openProduct(int index) {
        visible(productTitles);
        driver.findElements(productTitles).get(index).click();
        return new ProductDetailPage(driver);
    }
}
