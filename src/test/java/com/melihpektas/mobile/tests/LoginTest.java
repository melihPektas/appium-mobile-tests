package com.melihpektas.mobile.tests;

import com.melihpektas.mobile.data.TestData;
import com.melihpektas.mobile.pages.LoginPage;
import com.melihpektas.mobile.support.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(groups = "smoke", description = "A registered user logs in from the menu and returns to the catalog")
    public void registeredUserCanLogIn() {
        catalog();
        LoginPage login = header().openLoginFromMenu();

        login.loginAs(TestData.STANDARD_USER);

        catalog(); // throws if the catalog does not come back after login
    }

    @Test(description = "Submitting an empty form shows a username validation message")
    public void loginRequiresAUsername() {
        catalog();
        LoginPage login = header().openLoginFromMenu();

        login.login("", "");

        assertEquals(login.usernameError(), "Username is required");
    }
}
