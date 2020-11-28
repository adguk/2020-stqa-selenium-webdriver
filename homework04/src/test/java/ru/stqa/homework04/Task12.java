package ru.stqa.homework04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
 *
 * Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.
 *
 * Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
 *
 * Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу (о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).
 *
 * Картинку с изображением товара нужно уложить в репозиторий вместе с кодом. При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет. Надо средствами языка программирования преобразовать относительный путь в абсолютный.
 *
 * После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
 *
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 */
public class Task12 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final File PRODUCT_IMAGE = new File(this.getClass().getClassLoader().getResource("images/blue_duck.png").getFile());

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    void addNewProduct_should_addNewProduct() throws InterruptedException {
        // Login as admin
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
        waitForPageLoad();

        // Click Catalog
        driver.findElement(By.cssSelector("ul#box-apps-menu li:nth-child(2) a")).click();
        waitForPageLoad();
        // Click "Add New Product"
        List<WebElement> buttons = driver.findElements(By.cssSelector("td#content a.button"));
        assertThat(buttons).isNotEmpty().hasSize(2);
        buttons.get(1).click();
        Thread.sleep(1000);
        waitForPageLoad();

        // Fill General page
        // Click Enabled
        driver.findElement(By.cssSelector("input[type=radio][name=status][value='1']")).click();
        // Fill Name
        String randomUUID = UUID.randomUUID().toString();
        driver.findElement(By.cssSelector("input[type=text][name='name[en]']")).sendKeys(randomUUID);
        // Fill Code
        driver.findElement(By.cssSelector("input[type=text][name=code]")).sendKeys(randomUUID + " Code");
        // Unselect Categories root checkbox
        driver.findElement(By.cssSelector("input[type=checkbox][name='categories[]'][data-name=Root]")).click();
        // Select Categories Rubber Ducks checkbox
        driver.findElement(By.cssSelector("input[type=checkbox][name='categories[]'][data-name='Rubber Ducks']")).click();
        // Select Default Category Rubber Ducks
        Select defaultCategory = new Select(driver.findElement(By.cssSelector("select[name=default_category_id]")));
        defaultCategory.selectByVisibleText("Rubber Ducks");
        // Select Product Groups Unisex
        driver.findElement(By.cssSelector("input[type=checkbox][value='1-3']")).click();
        // Fill Quantity
        WebElement quantity = driver.findElement(By.cssSelector("input[type=number][name=quantity]"));
        quantity.clear();
        quantity.sendKeys("100");
        // Set Sold Out Status Temporarily sold out
        Select soldOutStatus = new Select(driver.findElement(By.cssSelector("select[name=sold_out_status_id]")));
        soldOutStatus.selectByVisibleText("Temporary sold out");
        // Fill Upload Images
        driver.findElement(By.cssSelector("input[type=file][name='new_images[]']")).sendKeys(PRODUCT_IMAGE.getAbsolutePath());
        // Fill Date Valid From
        driver.findElement(By.cssSelector("input[type=date][name=date_valid_from]")).sendKeys("01012020");
        // Fill Date Valid To
        driver.findElement(By.cssSelector("input[type=date][name=date_valid_to]")).sendKeys("01012520");

        // Fill Information page
        // Navigate to Information page
        driver.findElement(By.cssSelector("ul.index li:nth-child(2) a")).click();
        Thread.sleep(1000);
        waitForPageLoad();
        // Select Manufacturer ACME Corp.
        Select manufacturer = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]")));
        manufacturer.selectByVisibleText("ACME Corp.");
        // Fill Keywords
        driver.findElement(By.cssSelector("input[type=text][name=keywords]")).sendKeys(randomUUID + " Keywords");
        manufacturer.selectByVisibleText("ACME Corp.");
        // Fill Short Description
        driver.findElement(By.cssSelector("input[type=text][name='short_description[en]']")).sendKeys(randomUUID + " Short Description");
        // Fill Description
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(randomUUID + " Description");
        // Fill Head Title
        driver.findElement(By.cssSelector("input[type=text][name='head_title[en]']")).sendKeys(randomUUID + " Head Title");
        // Fill Meta Description
        driver.findElement(By.cssSelector("input[type=text][name='meta_description[en]']")).sendKeys(randomUUID + " Meta Description");

        // Fill Prices page
        // Navigate to Prices page
        driver.findElement(By.cssSelector("ul.index li:nth-child(4) a")).click();
        Thread.sleep(1000);
        waitForPageLoad();
        // Fill Purchase Price
        WebElement purchasePrice = driver.findElement(By.cssSelector("input[type=number][name='purchase_price']"));
        purchasePrice.clear();
        purchasePrice.sendKeys("10");
        Select purchasePriceCurrency = new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        purchasePriceCurrency.selectByVisibleText("US Dollars");
        // Fill Price USD
        driver.findElement(By.cssSelector("input[type=text][name='prices[USD]']")).sendKeys("100");
        // Fill Price EUR
        driver.findElement(By.cssSelector("input[type=text][name='prices[EUR]']")).sendKeys("84");
        // Add Campaign
        driver.findElement(By.cssSelector("a#add-campaign")).click();
        // Fill Start Date
        WebElement startDate = driver.findElement(By.cssSelector("input[type=datetime-local][name='campaigns[new_1][start_date]']"));
        startDate.sendKeys("01012020");
        startDate.sendKeys(Keys.TAB);
        startDate.sendKeys("0000");
        // Fill End Date
        WebElement endDate = driver.findElement(By.cssSelector("input[type=datetime-local][name='campaigns[new_1][end_date]']"));
        endDate.sendKeys("01012520");
        endDate.sendKeys(Keys.TAB);
        endDate.sendKeys("0000");
        // Fill Discount Percentage
        WebElement discountPercentage = driver.findElement(By.cssSelector("input[type=number][name='campaigns[new_1][percentage]']"));
        discountPercentage.clear();
        discountPercentage.sendKeys("20");
        // Save
        driver.findElement(By.cssSelector("button[name=save]")).click();
        Thread.sleep(1000);
        waitForPageLoad();

        // Check that product was successfully added
        WebElement created = driver.findElement(By.xpath("//a[contains(text(), '" + randomUUID + "')]"));
        assertThat(created.getAttribute("textContent")).isEqualTo(randomUUID);
    }

    @Test
    void mmmmm() {
        System.out.println(UUID.randomUUID().toString());
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
}
