package com.melihpektas.mobile.pages;

import com.melihpektas.mobile.data.TestData.User;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By username = id("nameET");
    private final By password = id("passwordET");
    private final By loginButton = id("loginBtn");
    private final By usernameError = id("nameErrorTV");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void loginAs(User user) {
        login(user.username(), user.password());
    }

    public void login(String user, String pass) {
        typeInto("nameET", user);
        typeInto("passwordET", pass);
        scrollTo("loginBtn").click();
    }

    public String usernameError() {
        return textOf(usernameError);
    }

    public boolean isShown() {
        return visible(loginButton).isDisplayed() && visible(username).isDisplayed() && visible(password).isDisplayed();
    }
}
