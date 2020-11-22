package ru.stqa.homework02.task07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task07 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 60);
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    void admin_should_have_leftSideMenuWithHeadersAfterSuccessfulLogin() {
        // Login
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        // Find menu list on the left side
        waitUntilMenuListIsPresent();

        // Check menu list items
        // 1. Check Appearance
        // Click Appearance
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(0)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Template page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(0)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-template"))
                .findElement(By.cssSelector("a"))
                .click();
        //        wait.until((WebDriver) -> !driver.findElements(By.cssSelector("td#content")).isEmpty());
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Logotype page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(0)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-logotype"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 2. Check Catalog
        // Click Catalog
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Catalog page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-catalog"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Product Groups page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-product_groups"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Option Groups page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-option_groups"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Manufacturers page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-manufacturers"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Suppliers page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-suppliers"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Delivery Statuses page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-delivery_statuses"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Sold Out Statuses page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-sold_out_statuses"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Quantity Units page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-quantity_units"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check CSV Import/Export page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(1)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-csv"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 3. Check Countries
        // Click Countries
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(2)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 4. Check Currencies
        // Click Currencies
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(3)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 5. Check Customers
        // Click Customers
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(4)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Customers page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(4)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-customers"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check CSV Import/Export page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(4)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-csv"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Newsletter page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(4)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-newsletter"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 6. Check Geo Zones
        // Click Geo Zones
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(5)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 7. Check Languages
        // Click Languages
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(6)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Languages page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(6)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-languages"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Storage Encoding page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(6)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-storage_encoding"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 8. Check Modules
        // Click Modules
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Background Jobs page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-jobs"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Customer page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-customer"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Shipping page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-shipping"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Payment page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-payment"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Order Total page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-order_total"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Order Success page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-order_success"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Order Action page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(7)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-order_action"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 9. Check Orders
        // Click Orders
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(8)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Orders page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(8)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-orders"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Orders Statuses page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(8)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-order_statuses"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 10. Check Pages
        // Click Pages
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(9)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 11. Check Reports
        // Click Reports
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(10)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Monthly Sales page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(10)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-monthly_sales"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Most Sold Products page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(10)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-most_sold_products"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Most Shopping Customers page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(10)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-most_shopping_customers"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 12. Check Settings
        // Click Settings
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Store Info page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-store_info"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Defaults page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-defaults"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check General page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-general"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Listings page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-listings"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Images page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-images"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Checkout page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-checkout"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Advanced page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-advanced"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Security page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(11)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-security"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 13. Check Slides
        // Click Slides
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(12)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 14. Check Tax
        // Click Tax
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(13)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Tax Classes page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(13)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-tax_classes"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Tax Rates page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(13)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-tax_rates"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 15. Check Translations
        // Click Translations
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(14)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Search Translations page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(14)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-search"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check Scan Files page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(14)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-scan"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check CSV Import/Export page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(14)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-csv"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 16. Check Users
        // Click Users
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(15)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // 17. Check vQmods
        // Click vQmods
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(16)
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();

        // Check vQmods page
        driver.findElement(By.cssSelector("ul#box-apps-menu"))
                .findElements(By.cssSelector("li#app-"))
                .get(16)
                .findElement(By.cssSelector("ul.docs"))
                .findElement(By.cssSelector("li#doc-vqmods"))
                .findElement(By.cssSelector("a"))
                .click();
        verifyH1Presense();
        waitUntilMenuListIsPresent();
    }

    private void verifyH1Presense() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td#content"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td#content > h1"), 1));
        List<WebElement> elements = driver.findElements(By.cssSelector("td#content"));
        assertThat(elements).isNotEmpty().hasSize(1);
        WebElement content = elements.get(0);
        List<WebElement> h1 = content.findElements(By.cssSelector("h1"));
        assertThat(h1).isNotEmpty().hasSize(1);
    }

    private void waitUntilMenuListIsPresent() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("body"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div#body-wrapper"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div#body"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div#content-wrapper"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td#sidebar"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div#box-apps-menu-wrapper"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("ul#box-apps-menu"), 1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("ul#box-apps-menu > li#app-"), 17));
    }
}
