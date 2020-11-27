package ru.stqa.homework04;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).
 *
 * Сценарий должен состоять из следующих частей:
 *
 * 1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
 * 2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
 * 3) повторный вход в только что созданную учётную запись,
 * 4) и ещё раз выход.
 *
 * В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.
 */
public class Task11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    void registerNewUser_should_completeSuccessfully() throws InterruptedException {
        // Navigate to the page
        driver.navigate().to("http://localhost/litecart/en/");
        waitForPageLoad();

        // Find and click link "New customers click here"
        driver.findElement(By.cssSelector("form[name=login_form] a")).click();
        waitForPageLoad();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost/litecart/en/create_account");

        // This unique identifier will be used for filling most fields in registration form
        String randomUUID = UUID.randomUUID().toString();

        List<WebElement> formTableRows = driver.findElements(By.cssSelector("div#create-account tr"));
        assertThat(formTableRows).isNotEmpty().hasSize(9);
        // Fill First Name
        formTableRows.get(1).findElement(By.cssSelector("input[name=firstname]")).sendKeys(randomUUID);
        // Fill Last Name
        formTableRows.get(1).findElement(By.cssSelector("input[name=lastname]")).sendKeys(randomUUID);
        // Fill Address 1
        formTableRows.get(2).findElement(By.cssSelector("input[name=address1]")).sendKeys(randomUUID);
        // Fill Postcode
        formTableRows.get(3).findElement(By.cssSelector("input[name=postcode]")).sendKeys("99999");
        // Fill City
        formTableRows.get(3).findElement(By.cssSelector("input[name=city]")).sendKeys(randomUUID);
        // Fill Country
        ((JavascriptExecutor)driver).executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", formTableRows.get(4).findElement(By.cssSelector("select[name=country_code]")));
        // Fill Zone/State/Province
        WebElement stateSelect = formTableRows.get(4).findElement(By.cssSelector("select[name=zone_code]"));
        stateSelect.click();
        List<WebElement> stateSelectOptions = stateSelect.findElements(By.cssSelector("option"));
        assertThat(stateSelectOptions).isNotEmpty().hasSize(65);
        stateSelectOptions.get(RandomUtils.nextInt(0, stateSelectOptions.size())).click();
        // Fill Email
        formTableRows.get(5).findElement(By.cssSelector("input[name=email]")).sendKeys(randomUUID + "@domain.com");
        // Fill Phone
        formTableRows.get(5).findElement(By.cssSelector("input[name=phone]")).sendKeys("9999999999");
        // Fill Desired Password
        formTableRows.get(7).findElement(By.cssSelector("input[name=password]")).sendKeys(randomUUID);
        // Confirm Password
        formTableRows.get(7).findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(randomUUID);
        // Fill Create Account
        formTableRows.get(8).findElement(By.cssSelector("button[name=create_account]")).click();
        waitForPageLoad();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost/litecart/en/");

        // Logout
        driver.findElement(By.cssSelector("div#box-account li:nth-child(4) a")).click();
        waitForPageLoad();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost/litecart/en/");

        // Login again with new user credentials
        // Fill Email Address
        driver.findElement(By.cssSelector("div#box-account-login input[name=email]")).sendKeys(randomUUID + "@domain.com");
        // Fill Password
        driver.findElement(By.cssSelector("div#box-account-login input[name=password]")).sendKeys(randomUUID);
        // Login
        driver.findElement(By.cssSelector("div#box-account-login button[name=login]")).click();
        waitForPageLoad();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost/litecart/en/");

        // Logout
        driver.findElement(By.cssSelector("div#box-account li:nth-child(4) a")).click();
        waitForPageLoad();
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost/litecart/en/");
    }

    @AfterEach
    void afterEach() {
        driver.quit();
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
