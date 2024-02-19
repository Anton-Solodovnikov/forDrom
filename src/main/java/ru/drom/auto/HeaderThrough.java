package ru.drom.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class HeaderThrough {
    private WebDriver driver;

    private By loginButton = By.cssSelector("[data-ga-stats-name='auth_block_login']");

    private By favoriteButton = By.xpath("//div[@data-ftid='component_header_user-info-expand-controller']/following-sibling::*//a[@data-ftid='component_header_my-favorite']");

    public HeaderThrough(WebDriver driver) {
        this.driver = driver;
    }

    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
    public void myFavoriteButtonClick(){
        driver.findElement(favoriteButton).click();
    }

}
