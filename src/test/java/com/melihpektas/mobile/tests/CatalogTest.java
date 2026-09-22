package com.melihpektas.mobile.tests;

import com.melihpektas.mobile.pages.CatalogPage;
import com.melihpektas.mobile.pages.ProductDetailPage;
import com.melihpektas.mobile.support.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CatalogTest extends BaseTest {

    @Test(groups = "smoke", description = "The catalog lists named products right after launch")
    public void catalogShowsProductsOnLaunch() {
        List<String> names = catalog().productNames();

        assertFalse(names.isEmpty(), "catalog should list products");
        assertTrue(names.stream().noneMatch(String::isBlank), "every product should have a name");
    }

    @Test(description = "Opening a product shows the details of that same product")
    public void openingAProductShowsItsDetails() {
        CatalogPage catalog = catalog();
        String firstProduct = catalog.productNames().get(0);

        ProductDetailPage details = catalog.openProduct(0);

        assertEquals(details.productName(), firstProduct);
    }
}
