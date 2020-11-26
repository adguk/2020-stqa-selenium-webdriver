package ru.stqa.homework03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.
 *
 * Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:
 *
 * а) на главной странице и на странице товара совпадает текст названия товара
 * б) на главной странице и на странице товара совпадают цены (обычная и акционная)
 * в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
 * г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
 * (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
 * д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
 *
 * Необходимо убедиться, что тесты работают в разных браузерах, желательно проверить во всех трёх ключевых браузерах (Chrome, Firefox, IE).
 */
public class Task10 {
    private WebDriver driver;
    private WebDriverWait wait;

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    void chrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        product_on_main_page_and_on_product_page_should_be_correct();
    }

    @Test
    void firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        product_on_main_page_and_on_product_page_should_be_correct();
    }

    private void product_on_main_page_and_on_product_page_should_be_correct() {
        // Navigate to the page
        driver.navigate().to("http://localhost/litecart/en/");
        waitForPageLoad();

        WebElement mainPageProduct = driver.findElement(By.cssSelector("div#box-campaigns li.product:first-child"));
        String mainPageProductName = mainPageProduct.findElement(By.cssSelector("div.name")).getAttribute("textContent");
        // Тег s сам по себе означает, что цена зачеркнута. Поэтому отдельно это не проверяется.
        WebElement mainPageProductRegularPrice = mainPageProduct.findElement(By.cssSelector("s.regular-price"));
        String mainPageProductRegularPriceTextContent = mainPageProductRegularPrice.getAttribute("textContent");
        String mainPageProductRegularPriceColor = mainPageProductRegularPrice.getCssValue("color");
        String mainPageProductRegularPriceSize = mainPageProductRegularPrice.getCssValue("font-size");
        // Тег strong сам по себе означает, что цена выделена жирным. Поэтому отдельно это не проверяется.
        WebElement mainPageProductCampaignPrice = mainPageProduct.findElement(By.cssSelector("strong.campaign-price"));
        String mainPageProductCampaignPriceTextContent = mainPageProductCampaignPrice.getAttribute("textContent");
        String mainPageProductCampaignPriceColor = mainPageProductCampaignPrice.getCssValue("color");
        String mainPageProductCampaignPriceSize = mainPageProductCampaignPrice.getCssValue("font-size");

        // Проверки для главной страницы
        // в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        isColorGray(mainPageProductRegularPriceColor, driver);
        // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        isColorRed(mainPageProductCampaignPriceColor, driver);
        // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        isCampaignPriceFontSizeBiggerThanRegularPriceFontSize(mainPageProductRegularPriceSize, mainPageProductCampaignPriceSize);

        // Переход на страницу товара
        mainPageProduct.findElement(By.cssSelector("a.link")).click();
        waitForPageLoad();

        WebElement productPageProduct = driver.findElement(By.cssSelector("div#box-product"));
        String productPageProductName = productPageProduct.findElement(By.cssSelector("h1.title[itemprop=name]")).getAttribute("textContent");
        // Тег s сам по себе означает, что цена зачеркнута. Поэтому отдельно это не проверяется.
        WebElement productPageProductRegularPrice = productPageProduct.findElement(By.cssSelector("div.price-wrapper[itemprop=offers] > s.regular-price"));
        String productPageProductRegularPriceTextContent = productPageProductRegularPrice.getAttribute("textContent");
        String productPageProductRegularPriceColor = productPageProductRegularPrice.getCssValue("color");
        String productPageProductRegularPriceSize = productPageProductRegularPrice.getCssValue("font-size");
        // Тег strong сам по себе означает, что цена выделена жирным. Поэтому отдельно это не проверяется.
        WebElement productPageProductCampaignPrice = productPageProduct.findElement(By.cssSelector("div.price-wrapper[itemprop=offers] > strong.campaign-price"));
        String productPageProductCampaignPriceTextContent = productPageProductCampaignPrice.getAttribute("textContent");
        String productPageProductCampaignPriceColor = productPageProductCampaignPrice.getCssValue("color");
        String productPageProductCampaignPriceSize = productPageProductCampaignPrice.getCssValue("font-size");

        // Проверки для страницы продукта
        // в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        isColorGray(productPageProductRegularPriceColor, driver);
        // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        isColorRed(productPageProductCampaignPriceColor, driver);
        // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        isCampaignPriceFontSizeBiggerThanRegularPriceFontSize(productPageProductRegularPriceSize, productPageProductCampaignPriceSize);

        // а) на главной странице и на странице товара совпадает текст названия товара
        assertThat(mainPageProductName).isEqualTo(productPageProductName);
        // б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        assertThat(mainPageProductRegularPriceTextContent).isEqualTo(productPageProductRegularPriceTextContent);
        assertThat(mainPageProductCampaignPriceTextContent).isEqualTo(productPageProductCampaignPriceTextContent);
    }

    private void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        wait.until(pageLoadCondition);
    }

    private static boolean isColorGray(String cssColor, WebDriver driver) {
        String[] rgbaNumbers = getRgbaNumbers(cssColor, driver);
        return rgbaNumbers[0].equals(rgbaNumbers[1]) && rgbaNumbers[0].equals(rgbaNumbers[2]);
    }

    private static boolean isColorRed(String cssColor, WebDriver driver) {
        String[] rgbaNumbers = getRgbaNumbers(cssColor, driver);
        return rgbaNumbers[1].equals("0") && rgbaNumbers[2].equals("0");
    }

    // Chrome example - rgba(119, 119, 119, 1)
    // Firefox example - rgb(119, 119, 119)
    private static String[] getRgbaNumbers(String cssColor, WebDriver driver) {
        String[] rgbaNumbers;
        // Chrome
        if (driver instanceof ChromeDriver) {
            rgbaNumbers = cssColor.replace("rgba(", "").replace(")", "").split(", ");
            assertThat(rgbaNumbers).isNotEmpty().hasSize(4);
        }
        else if (driver instanceof FirefoxDriver) {
            rgbaNumbers = cssColor.replace("rgb(", "").replace(")", "").split(", ");
            assertThat(rgbaNumbers).isNotEmpty().hasSize(3);
        }
        else
            throw new RuntimeException("Unsupported driver class");
        return rgbaNumbers;
    }

    private static boolean isCampaignPriceFontSizeBiggerThanRegularPriceFontSize(String regularPriceFontSize, String campaignPriceFontSize) {
        Double regularSize = Double.parseDouble(regularPriceFontSize.replace("px", ""));
        Double campaignSize = Double.parseDouble(campaignPriceFontSize.replace("px", ""));
        return campaignSize > regularSize;
    }
}
