import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BassVTest {
    public WebDriver driver;
    public String baseUrl;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://shop.fender.com";
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    @Test
    public void testBassV() throws Exception {
        driver.get(baseUrl + "/en-US/");
        driver.findElement(By.cssSelector("#footer > div:nth-child(5) > div > nav > div > ul > li:nth-child(2) > ul > li:nth-child(2) > a")).click();
        driver.findElement(By.cssSelector("#bc5G6iaag1k8QaaadefNa4i9o7 > div.product-image > a.thumb-link > img")).click();
        String bassPrice = driver.findElement(By.cssSelector("span.price-sales")).getText();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart\"]")).click();
        Thread.sleep(1000);
        driver.get(baseUrl + "/en-US/cart");
        assertEquals(bassPrice, driver.findElement(By.xpath("//div[@id='wrapper']/main/section[2]/div/div[2]/div/div[2]/div/table/tbody/tr/td[2]")).getText());
        driver.findElement(By.cssSelector("html.js.no-touch.svg.no-ie10.no-ie11 body div#wrapper.pt_cart main section.module-content.vpad.module.sm div.row.right-column-cart-container div.column-3 div.cart-right-column.hidden-mobile div.sticky-cart-column div.vspace.component.subtotal-right-column.border-top form#checkout-form.cart-action-checkout.vpad.element fieldset button.button-fancy-large.large.full.green-btn")).click();
        driver.findElement(By.name("dwfrm_login_unregistered")).click();
        assertEquals(bassPrice, driver.findElement(By.xpath("//div[@id='secondary']/div[2]/table/tbody/tr/td[2]")).getText());
        assertEquals("1", driver.findElement(By.xpath("//div[@id='secondary']/div/div/div[3]/div/div[2]/p/span[2]")).getText());
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("Josh");
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("sellers");
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("123 some place");
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("Winnetka");
        new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state"))).selectByVisibleText("California");
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).sendKeys("91306");
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).clear();
        driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).sendKeys("8188881111");
        driver.findElement(By.xpath("//*[@id=\"main\"]/section[2]/div/div[2]/div[2]/div[2]/div[2]/label/span")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        }
    }
