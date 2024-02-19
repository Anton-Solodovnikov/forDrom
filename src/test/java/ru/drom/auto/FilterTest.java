package ru.drom.auto;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FilterTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Проверка результатов выдачи объявлений после установки фильтра")
    public void setFilterResultTest() {
        driver.get("https://auto.drom.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.setFilter();
        mainPage.showButtonClick();
        for (int i = 1; i <= mainPage.getNumberOfAds(); i = i + 1) {
            assertFalse("На странице есть проданные автомобили", mainPage.isSoldCar(i));
            assertTrue("Дата выпуска меньше чем 2007",mainPage.getAdTitle(i) >= 2007);
            MatcherAssert.assertThat(mainPage.getMileage(i), notNullValue());
        }
        mainPage.secondPageClick();
        for (int i = 1; i <= mainPage.getNumberOfAds(); i = i + 1) {
            assertFalse("На странице есть проданные автомобили", mainPage.isSoldCar(i));
            assertTrue("Дата выпуска меньше чем 2007",mainPage.getAdTitle(i) >= 2007);
            MatcherAssert.assertThat(mainPage.getMileage(i), notNullValue());
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
