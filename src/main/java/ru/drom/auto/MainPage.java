package ru.drom.auto;


import io.qameta.allure.Step;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.Comparator;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MainPage extends HeaderThrough{
    private WebDriver driver;

    private By advancedFilterButton = By.cssSelector("[data-ftid='sales__filter_advanced-button']");
    private By brandSelect = By.cssSelector("[data-ftid='sales__filter_fid']");
    private By toyotaBrand = By.xpath(".//div[@data-ftid = 'sales__filter_fid']//div[contains(text(),'Toyota')]");
    private By modelSelect = By.cssSelector("[data-ftid='sales__filter_mid']");
    private By modelImput = By.cssSelector("[placeholder='Модель']");
    private By harrierModel = By.xpath(".//div[@data-ftid = 'sales__filter_mid']//div[contains(text(),'Harrier')]");
    private By fuelSelect = By.cssSelector("[data-ftid='sales__filter_fuel-type']");
    private By hybridFuel = By.xpath(".//div[@data-ftid = 'sales__filter_fuel-type']//div[contains(text(),'Гибрид')]");
    private By unsoldCheckbox = By.cssSelector("[for='sales__filter_unsold']");
    private By mileageFromInput = By.cssSelector("[data-ftid='sales__filter_mileage-from']");
    private By yearFromSelect = By.cssSelector("[data-ftid='sales__filter_year-from']");
    private By yearFrom2007 = By.xpath(".//div[@data-ftid = 'sales__filter_year-from']//div[text()='2007']");
    private By showButton = By.cssSelector("[data-ftid='sales__filter_submit-button']");
    private By adsOnPage = By.xpath(".//a[@data-ftid = 'bulls-list_bull']");
    private By secondPageItem = By.xpath(".//a[contains(@data-ftid, 'component_pagination-item') and contains(text(), '2')]");
    private By region = By.xpath(".//div[text() = 'Приморский край']/..");
    private By firstAd = By.xpath(".//a[@data-ftid = 'bulls-list_bull'][1]");
    private By firstAdTitile = By.xpath(".//a[@data-ftid = 'bulls-list_bull'][1]//span[@data-ftid='bull_title']");
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(this.driver,3);
    }

    WebDriverWait wait;


    public void scrollToElement(By element) {
        WebElement e = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", e);
    }

    public boolean isSoldCar(Integer index) {
        boolean result;
        String indexString = index.toString();
        By oneAd = By.xpath(".//a[@data-ftid = 'bulls-list_bull']["+ indexString +"]");
        By adsSoldStatus = By.xpath(".//a[@data-ftid = 'bulls-list_bull']["+ indexString +"]"+ "//div[text()='снят с продажи']");
        try {
            scrollToElement(oneAd);
            result = driver.findElement(adsSoldStatus).isDisplayed();
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }
    public int getAdTitle(Integer index) {
        int result;
        String indexString = index.toString();
        By AdTitle = By.xpath(".//a[@data-ftid = 'bulls-list_bull']["+ indexString + "]//span[@data-ftid='bull_title']");
        scrollToElement(AdTitle);
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(AdTitle)).getText();
        result = Integer.parseInt(title.substring(title.length() - 4));
        return result;
    }
    public String getMileage(Integer index) {
        String indexString = index.toString();
        By AdMileage = By.xpath(".//a[@data-ftid = 'bulls-list_bull']["+ indexString + "]//span[contains(text(),'км')]");
        return driver.findElement(AdMileage).getText();
    }
    public void advertisLineClick() {
        driver.findElement(firstAd).click();
    }
    public String getAdvertisLineName(){
        scrollToElement(firstAdTitile);
        return driver.findElement(firstAdTitile).getText();
    }
    public void advancedFilterButtonClick() {
        scrollToElement(advancedFilterButton);
        wait.until(ExpectedConditions.elementToBeClickable(advancedFilterButton)).click();
    }

    public void brandSelectClick() {
        scrollToElement(brandSelect);
        wait.until(ExpectedConditions.elementToBeClickable(brandSelect)).click();
    }
    public void toyotaBrandClick() {
        wait.until(ExpectedConditions.elementToBeClickable(toyotaBrand)).click();
    }
    public void modelSelectClick() {
        wait.until(ExpectedConditions.elementToBeClickable(modelSelect)).click();
    }
    public void modelSelectSetText(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(modelImput)).sendKeys(text);
    }
    public void harrierModelClick() {
        wait.until(ExpectedConditions.elementToBeClickable(harrierModel)).click();
    }
    public void fuelSelectClick() {
        driver.findElement(fuelSelect).click();
    }
    public void hybridFuelClick() {
        driver.findElement(hybridFuel).click();
    }
    public void unsoldCheckboxClick() {
        driver.findElement(unsoldCheckbox).click();
    }
    public void mileageFromInputSetText(String text) {
        driver.findElement(mileageFromInput).sendKeys(text);
    }
    public void yearFromSelectClick() {
        driver.findElement(yearFromSelect).click();
    }
    public void yearFrom2007Click() {
        driver.findElement(yearFrom2007).click();
    }
    public void showButtonClick() {
        driver.findElement(showButton).click();
    }
    public void secondPageClick() {
        driver.findElement(secondPageItem).click();
    }
    public int getNumberOfAds() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adsOnPage)).size();
    }
    public void regionClick(){
        driver.findElement(region).click();
    }
    public void setFilter() {
        advancedFilterButtonClick();
        brandSelectClick();
        toyotaBrandClick();
        fuelSelectClick();
        hybridFuelClick();
        modelSelectClick();
        modelSelectSetText("har");
        harrierModelClick();
        unsoldCheckboxClick();
        mileageFromInputSetText("1");
        yearFromSelectClick();
        yearFrom2007Click();
    }
    private static Comparator<Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };

    public void getTopBrandsOnDrom() {
        BidiMap<String, Integer> brandsMap = new TreeBidiMap<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        Integer index = 212;
        regionClick();
        brandSelectClick();
        boolean stop = false;
        while (stop == false) {
            By options = By.xpath(".//div[@data-ftid = 'sales__filter_fid']//div[@style='height: 35px; transform: translateY("+ index.toString() +"px);']/div");
                wait.until(ExpectedConditions.visibilityOfElementLocated(options));
                scrollToElement(options);
                String valueOfOptions = driver.findElement(options).getText();
                if (valueOfOptions.contains("(")) {
                    String[] split = valueOfOptions.split("\\(");
                    int formatSplit2 = Integer.parseInt(split[1].substring(0,split[1].length() - 1));
                    brandsMap.put(split[0],formatSplit2);
                    quantity.add(formatSplit2);
                }
                index = index + 35;
            if (valueOfOptions.contains("УАЗ")) {
                stop = true;
            }
        }
            quantity.sort(INTEGER_COMPARATOR.reversed());
            System.out.println("| Фирма | Количество объявлений |");
            for (int i = 0; i <= 19; i = i + 1) {
                int brandValue = quantity.get(i);
                String result = "|"+ brandsMap.getKey(brandValue)+"|"+quantity.get(i)+"|";
                System.out.println(result);
                LogUtil.log(result);
            }

    }


}
