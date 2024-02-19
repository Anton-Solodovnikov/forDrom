package ru.drom.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TopBrandTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Вывод топ 20 марок авто с самым большим количеством объявлений в приморском крае")
    public void getTopBrand() {
        driver.get("https://auto.drom.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.getTopBrandsOnDrom();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
