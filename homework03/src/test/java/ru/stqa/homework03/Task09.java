package ru.stqa.homework03;

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

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
 */
public class Task09 {
    private WebDriver driver;
    private WebDriverWait wait;

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

    /**
     * 1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
     * а) проверить, что страны расположены в алфавитном порядке
     * б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
     */
    @Test
    void countries_page_should_contain_countries_in_alphabetical_order() {
        // Login as admin
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        waitForPageLoad();

        // Navigate to the page
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        waitForPageLoad();

        // Collect country links
        List<WebElement> countryLinks = driver.findElements(By.cssSelector("tr.row > td:nth-child(5) > a"));
        assertThat(countryLinks).isNotEmpty();

        // Get country names from country links
        List<String> countryNames = countryLinks.stream()
                .map(webElement -> webElement.getAttribute("textContent"))
                .collect(Collectors.toList());
        assertThat(countryNames).isNotEmpty().hasSize(countryLinks.size());

        // а) проверить, что страны расположены в алфавитном порядке
        assertThat(isSorted(countryNames)).isTrue();

        // б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
        List<Integer> zones = driver.findElements(By.cssSelector("tr.row > td:nth-child(6)")).stream()
                .map(webElement -> webElement.getAttribute("textContent"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        assertThat(zones).isNotEmpty().hasSize(countryLinks.size());

        int zoneNumbers = zones.size();
        for (int i = 0; i < zoneNumbers; i++) {
            if (zones.get(i) > 0) {
                countryLinks = driver.findElements(By.cssSelector("tr.row > td:nth-child(5) > a"));
                countryLinks.get(i).click();
                waitForPageLoad();

                // Get list of zone columns
                List<WebElement> zoneColumns = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3)"));
                assertThat(zoneColumns).isNotEmpty();

                // Get list of zone names and filter away last column as it is for input and empty
                List<String> zoneNames = zoneColumns.stream()
                        .map(webElement -> webElement.getAttribute("textContent"))
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
                assertThat(zoneNames).isNotEmpty().hasSize(zoneColumns.size() - 1);
                assertThat(isSorted(zoneNames)).isTrue();

                // Navigate back
                driver.navigate().back();
                waitForPageLoad();
            }
        }
    }

    /**
     * 2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
     * зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
     */
    @Test
    void countries_from_geozones_page_should_contain_geozones_in_alphabetical_order() {
        // Login as admin
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        waitForPageLoad();

        // Navigate to the page
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        waitForPageLoad();

        // Collect country links
        List<WebElement> countryLinks = driver.findElements(By.cssSelector("tr.row > td:nth-child(3) > a"));
        assertThat(countryLinks).isNotEmpty();

        int countryLinksNumber = countryLinks.size();
        for (int i = 0; i < countryLinksNumber; i++) {
            countryLinks = driver.findElements(By.cssSelector("tr.row > td:nth-child(3) > a"));
            countryLinks.get(i).click();
            waitForPageLoad();

            // Get list of zone names
            List<String> zoneNames = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3) > select > option[selected=selected]")).stream()
                    .map(webElement -> webElement.getAttribute("textContent"))
                    .collect(Collectors.toList());
            assertThat(zoneNames).isNotEmpty();
            assertThat(isSorted(zoneNames)).isTrue();

            // Navigate back
            driver.navigate().back();
            waitForPageLoad();
        }
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

    private static boolean isSorted(List<String> listOfStrings) {
        Iterator<String> iter = listOfStrings.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
