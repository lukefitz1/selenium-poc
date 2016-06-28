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
import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;
import org.testng.ITestContext;

public class CheckoutTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Header header;
	private ProductPage prod;
	private Checkout checkout;
	private String[] baseUrls;
	private String url;
	private String siteEnv;

	@Parameters({"pbrowser", "pversion", "pos", "purl", "base_urls", "env", "pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, String base_urls, String env, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		baseUrls = base_urls.split(",");
		siteEnv = new String(env);

		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}

	private int param;
 	
    @Factory(dataProvider = "usesParameter")
    public CheckoutTest(int param) {
        this.param = param;
    }

    @DataProvider(name = "usesParameter")
    public static Object[][] dataMethod(ITestContext context) {
    	String base_urls = context.getCurrentXmlTest().getParameter("base_urls");
    	String[] urls = base_urls.split(",");
    	switch (urls.length) {
    		case 1: 
    			return new Object[][] { { 0 }};
    		case 2: 
    			return new Object[][] { { 0 }, { 1 }};
        	case 3: 
        		return new Object[][] { { 0 }, { 1 }, { 2 }};
    		default: 
    			return new Object[][] { { 0 }};
    	}
    }
    @Parameters({"purl_key"})
	@Test(groups = { "functional" }, priority = 0)
	public void goToSimpleTestProductPage(String purl_key) {
		System.out.println("Runing CheckoutTest...");
		url = "http://"  + siteEnv + "." +baseUrls[param];
		prod = new ProductPage(driver);
		prod.goToSimpleTestProductPage(url, purl_key);
	}
	@Parameters({"psimple_add_to_cart_button_selector", "pemail_pop_up" })
	@Test(groups = { "functional" }, priority = 1)
	public void addToCartButtonCheck(String psimple_add_to_cart_button_selector, String pemail_pop_up) {
		Assert.assertTrue(prod.simpleAddToCartButtonDisplayed(psimple_add_to_cart_button_selector, pemail_pop_up), "Simple product add to cart page not displayed");
	}
	@Parameters({"psimple_add_to_cart_button_selector"})
	@Test(groups = { "functional" }, priority = 2)
	public void addProductToCart(String psimple_add_to_cart_button_selector) {
		header = prod.addSimpleToCart(psimple_add_to_cart_button_selector);
		Assert.assertTrue(header.miniCartOpen(), "Mini cart did not open");
	}
	@Parameters({"pview_cart_selector"})
	@Test(groups = { "functional" }, priority = 3)
	public void viewCart(String pview_cart_selector) {
		header = prod.viewCart(pview_cart_selector);
		Assert.assertTrue(header.cartOpen(), "Cart did not open");
	}
	@Parameters({ "pproceed_to_checkout_button_selector", "pproduct_pop_up", "pcheckout_form"})
	@Test(groups = { "functional" }, priority = 4)
	public void proceedToCheckout(String pproceed_to_checkout_button_selector, String pproduct_pop_up, String pcheckout_form) {
		checkout = prod.proceedToCheckout(pproceed_to_checkout_button_selector, pproduct_pop_up);
		Assert.assertTrue(checkout.checkoutFormDisplayed(pcheckout_form), "Checkout form is displayed");
	}
	@Parameters({"puser", "ppass", "pcheckout_login_button_selector", "puser_user_name", "ppass_word_name"})
	@Test(groups = { "functional" }, priority = 5)
	public void proceedToCheckoutLogin(String puser, String ppass, String pcheckout_login_button_selector, String puser_user_name, String ppass_word_name) {
		checkout = prod.proceedToCheckoutLogin(puser, ppass, pcheckout_login_button_selector, puser_user_name, ppass_word_name);
		Assert.assertTrue(checkout.shippingInfoFormDisplayed(), "Shipping Info form is displayed");
	}
	@Parameters({"pbilling_button_selector"})
	@Test(groups = { "functional" }, priority = 6)
	public void checkoutShippingInfo(String pbilling_button_selector) {
		checkout = prod.checkoutShippingInfo(pbilling_button_selector);
		Assert.assertTrue(checkout.shippingMethodFormDisplayed(), "Shipping Method form is displayed");
	}
	@Parameters({"pshipping_method_selector", "pshipping_button_selector"})
	@Test(groups = { "functional" }, priority = 7)
	public void checkoutShippingMethod(String pshipping_method_selector, String pshipping_button_selector) {
		checkout = prod.checkoutShippingMethod(pshipping_method_selector, pshipping_button_selector);
		Assert.assertTrue(checkout.paymentMethodFormDisplayed(), "Payment Method form is displayed");
	}
	@Parameters({"ppayment_type"})
	@Test(groups = { "functional" }, priority = 8)
	public void checkoutPaymentMethod(String ppayment_type) {
		checkout = prod.checkoutPaymentMethod(ppayment_type);
		Assert.assertTrue(checkout.reviewOrderFormDisplayed(), "Review Order form is displayed");
	}
	@Test(groups = { "functional" }, priority = 9)
	public void checkoutReviewOrder() {
		checkout = prod.checkoutReviewOrder();
		Assert.assertTrue(checkout.confirmationFormDisplayed(), "Confirmation/Order number form is displayed");
	}
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
