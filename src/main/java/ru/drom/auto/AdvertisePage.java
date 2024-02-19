package ru.drom.auto;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdvertisePage extends HeaderThrough{
    private WebDriver driver;
    private By favoriteButton = By.cssSelector("[data-ftid='component_favorite_button_add']");
    public AdvertisePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void favoriteButtonClick() {
        driver.findElement(favoriteButton).click();
    }

}
