import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class LINETests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://line-id.iprice.mx");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement homeBanner = driver.findElementByClassName("banner-section");
        homeBanner.click();
        driver.navigate().back();

        WebElement recommendedCollection = driver.findElementByCssSelector("a[data-vars-lb*='type: carousel | position: 2']");
        recommendedCollection.click();
        driver.navigate().back();

        WebElement popularCategory = driver.findElementByCssSelector("a[data-vars-lb*='type: list | position: 4']");
        popularCategory.click();
        driver.navigate().back();

        String[] urls = {"blog", "koleksi", "diskon"};
        for (String url : urls){
            driver.get("https://line-id.iprice.mx/" + url + "/faq");
        };

        WebElement homeButton = driver.findElementByCssSelector("a[data-vars-lb='Home Button']");
        homeButton.click();

        WebElement homeSearch = driver.findElementByCssSelector("input[role='search']");
        homeSearch.sendKeys("air fryer", Keys.RETURN);

        WebElement sortByPriceAsc = driver.findElementByCssSelector("a[data-vars-lb='Price']");
        sortByPriceAsc.click();

        WebElement sortByPriceDesc = driver.findElementByCssSelector("a[data-vars-lb='Price']");
        sortByPriceDesc.click();

        WebElement sortByPopularity = driver.findElementByCssSelector("a[data-vars-lb='Popularity']");
        sortByPopularity.click();

        List<WebElement> pagination = driver.findElementsByCssSelector("#pagination > ul > li");
        pagination.stream().filter(paginationElement -> paginationElement
        .getText()
        .trim()
        .equals("2")).findFirst().orElseThrow(() -> new NoSuchElementException("No such page is found")).click();

        WebElement nextPage = driver.findElementByLinkText("Selanjutnya");
        nextPage.click();

        driver.get("https://line-id.iprice.mx/search/?term=air+fryer&sort=popularity_desc");

        WebElement PLPSearch = driver.findElementByCssSelector("input[role='search']");
        PLPSearch.clear();
        PLPSearch.sendKeys("iphone 11", Keys.RETURN);

        WebElement nonComparableProduct = driver.findElementByCssSelector("a[data-vars-action='shop']");
        nonComparableProduct.click();
        driver.navigate().back();

        WebElement comparableProduct = driver.findElementByCssSelector("a[data-vars-cgt*='comparable']");
        comparableProduct.click();

        WebElement productSpecs = driver.findElementByCssSelector("a[data-vars-cgt*='product_characteristic']");
        productSpecs.click();

        WebElement relatedProducts = driver.findElementByCssSelector("a[data-vars-cgt*='related_product']");
        relatedProducts.click();

        WebElement moreOffers = driver.findElementByCssSelector("div[data-vars-cgt*='more_offers']");
        moreOffers.click();

        WebElement clickOffer = driver.findElementByCssSelector("a[data-vars-action='pc']");
        clickOffer.click();

        System.out.println(driver.getWindowHandles());
        String parentHandle = driver.getWindowHandle();
        System.out.println(parentHandle);
        for (String handle : driver.getWindowHandles()){
            if(!parentHandle.equals(handle)){
                System.out.println(handle);
                driver.switchTo().window(handle);
            }
        }

        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body"))
        );
        driver.close();
    }
}