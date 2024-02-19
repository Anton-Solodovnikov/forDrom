package ru.drom.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddFavoriteAdsTest {
    private WebDriver driver;
    private String adsName;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Добавление объявления в избранное")
    public void addFavoriteAdsTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdvertisePage advertisePage = new AdvertisePage(driver);
        MyFavoriteAdsPage myFavoriteAdsPage = new MyFavoriteAdsPage(driver);
        driver.get("https://auto.drom.ru/");
        mainPage.loginButtonClick();
        loginPage.setLogin("79374576904");
        loginPage.setPassword("Test123");
        loginPage.signInButtonClick();
        adsName = mainPage.getAdvertisLineName();
        mainPage.advertisLineClick();
        advertisePage.favoriteButtonClick();
        advertisePage.myFavoriteButtonClick();
        myFavoriteAdsPage.checkTitleOfAds(adsName);
    }

    @After
    public void tearDown() {
        MyFavoriteAdsPage myFavoriteAdsPage = new MyFavoriteAdsPage(driver);
        myFavoriteAdsPage.deleteAd(adsName);
        driver.quit();
    }
}
