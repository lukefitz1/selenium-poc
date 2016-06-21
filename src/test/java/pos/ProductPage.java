package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pos.Header;
import pos.Checkout;
import org.openqa.selenium.JavascriptExecutor;

public class ProductPage extends Base {

	String simpleTestProductUrl = "ba-test-simple-product";
	String configTestProductUrlColor = "batest-config4";
	String user = "fet_cf_rm@blueacorn.com";
	String pass = "pass4fet_cf_rm";
	String payment = "Check/Mo"; //"Card";
	By userName = By.name("login[username]");
	By passWord = By.name("login[password]");
	By simpleAddToCart = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button");
	By viewCart = By.cssSelector("body > div.fancybox-wrap.fancybox-desktop.fancybox-type-html.minicart-wrap.fancybox-opened > div > div > div > div.cart.display-single-price > form > div.minicart-bottom > div.edit-bag > a");
	By configAddToCart = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button");
	By proceedToCheckoutButton = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.page-title.title-buttons > ul > li > button:nth-child(1)");
	By chekoutLoginButton = By.cssSelector("#checkout-step-login > div > div.col-2 > div > button");
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToSimpleTestProductPage(String url) {
		System.out.println("Go to " + url + simpleTestProductUrl);
		visit(url + simpleTestProductUrl);
	}
	
	public void goToColorConfigTestProductPage() {
		visit(configTestProductUrlColor);
	}
	
	public Boolean simpleAddToCartButtonDisplayed() {
		return waitForIsDisplayed(simpleAddToCart);
	}
	
	public Header addSimpleToCart() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button').click()");
		//click(simpleAddToCart);
		return new Header(driver);
	}
	public Header viewCart() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('body > div.fancybox-wrap.fancybox-desktop.fancybox-type-html.minicart-wrap.fancybox-opened > div > div > div > div.cart.display-single-price > form > div.minicart-bottom > div.edit-bag > a').click()");
		//click(viewCart);
		return new Header(driver);
	}
	public Checkout proceedToCheckout() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.page-title.title-buttons > ul > li > button:nth-child(1)').click()");
		//click(viewCart);
		return new Checkout(driver);
	}
	public Checkout proceedToCheckoutLogin() {
		type(user,userName);
		type(pass, passWord);
		click(chekoutLoginButton);
		return new Checkout(driver);
	}
	public Checkout checkoutShippingInfo() {
		driver.findElement(By.cssSelector("#billing-address-select > option:nth-child(2)")).click();
		driver.findElement(By.id("billing:firstname")).sendKeys("Nick");
        driver.findElement(By.id("billing:lastname")).sendKeys("Ata");
        driver.findElement(By.id("billing:company")).sendKeys("Blue Acorn");
        //driver.findElement(By.id("billing:email")).sendKeys("fet_cf_rm@blueacorn.com");
        driver.findElement(By.id("billing:street1")).sendKeys("148 Williman Street");
        driver.findElement(By.id("billing:city")).sendKeys("Charleston");
        //Dropdown
        driver.findElement(By.cssSelector("#billing\\3a region_id > option:nth-child(42)")).click();
        driver.findElement(By.id("billing:region_id")).sendKeys("South Carolina");
        driver.findElement(By.id("billing:postcode")).sendKeys("29403");
        //Dropdown preselected
        driver.findElement(By.id("billing:telephone")).sendKeys("1234567890");
        driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();
		return new Checkout(driver);
	}
	public Checkout checkoutShippingMethod() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('#checkout-shipping-method-load > dl > dd > ul > li.block_s_method_ups_3DS > label').click()");
		//driver.findElement(By.cssSelector("#checkout-shipping-method-load > dl > dd > ul > li.block_s_method_ups_3DS > label")).click();
		driver.findElement(By.cssSelector("#shipping-continue-button")).click();
		return new Checkout(driver);
	}
	public Checkout checkoutPaymentMethod() {
		if ( payment == "Card") {
			driver.findElement(By.id("braintree_cc_number")).sendKeys("4242424242424242");
	        driver.findElement(By.id("braintree_cc_cid")).sendKeys("123");
	        driver.findElement(By.cssSelector("#braintree_expiration > option:nth-child(3)")).click();
	        driver.findElement(By.cssSelector("#braintree_expiration_yr > option:nth-child(4)")).click();
		}
		if ( payment == "Check/Mo") {
			driver.findElement(By.cssSelector("#checkout-payment-method-load > dt:nth-child(3) > label")).click();
		}
		driver.findElement(By.cssSelector("#payment-buttons-container > button")).click();
		return new Checkout(driver);
	}
	public Checkout checkoutReviewOrder() {
		driver.findElement(By.cssSelector("#review-buttons-container > button")).click();
		return new Checkout(driver);
	}

}
