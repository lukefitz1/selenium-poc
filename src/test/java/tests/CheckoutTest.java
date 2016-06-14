package tests;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pos.Checkout;
import pos.Header;
import pos.ProductPage;

public class CheckoutTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Header header;
	private ProductPage prod;
	private Checkout checkout;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		
		driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(groups = { "functional" }, priority=0)
	public void goToSimpleTestProductPage() {
		prod = new ProductPage(driver);
		prod.goToSimpleTestProductPage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void addToCartButtonCheck() {
		Assert.assertTrue(prod.simpleAddToCartButtonDisplayed(), "Simple product add to cart page not displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void addProductToCart() {
		header = prod.addSimpleToCart();
		Assert.assertTrue(header.miniCartOpen(), "Mini cart did not open");
	}
	
	@Test(groups = { "functional" }, priority=9) 
	public void waitForSpinnerToGoAway() {
		Assert.assertTrue(header.miniCartSpinnerNotDisplayed(), "Spinner did not go away");
	}	
	
	@Test(groups = { "functional" }, priority=12) 
	public void miniCartCheckoutButtonCheck() {
		Assert.assertTrue(header.miniCartCheckoutButtonDisplayed(), "Mini cart checkout button displayed");
	}
	
	@Test(groups = { "functional" }, priority=15) 
	public void clickMiniCartCheckoutButton() {
		checkout = header.clickMiniCartCheckoutButton();
		Assert.assertTrue(checkout.checkoutFormDisplayed(), "Checkout form is displayed");
	}	
	
	@Test(groups = { "functional" }, priority=18) 
	public void checkoutUrlCheck() {
		Assert.assertEquals(driver.getCurrentUrl(), checkout.getBaseUrl() + checkout.getOneStepCheckoutUrl());
	}	
	
	/*
	 * 
	 * Begin filling out checkout form 
	 * 
	 */
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
