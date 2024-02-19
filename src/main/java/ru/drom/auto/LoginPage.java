package ru.drom.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By loginInput = By.id("sign");
    private By passwordInput = By.id("password");
    private By signInButton = By.id("signbutton");

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    public void setLogin (String login){
        driver.findElement(loginInput).sendKeys(login);
    }
    public void setPassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void signInButtonClick(){
        driver.findElement(signInButton).click();
    }
}
