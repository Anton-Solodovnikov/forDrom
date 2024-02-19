package ru.drom.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class MyFavoriteAdsPage {
    private WebDriver driver;

    public MyFavoriteAdsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitleOfAds(String expected) {
        By title = By.xpath(".//a[contains (text(), '"+expected+"')]");
        String result = driver.findElement(title).getText();
        assertTrue("Название избранного объявления не совпадает",result.contains(expected));
    }

    public void deleteAd(String expected) {
        By deleteButton = By.xpath(".//a[contains (text(), '"+expected+"')]/ancestor::div//.//div[@class='favorites-list_remove-action']");
        driver.findElement(deleteButton).click();
    }

}
