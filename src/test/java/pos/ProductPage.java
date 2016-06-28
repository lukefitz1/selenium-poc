package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pos.Header;
import pos.Checkout;
import org.openqa.selenium.JavascriptExecutor;

public class ProductPage extends Base {

	//String simpleTestProductUrl = "ba-test";
	//String configTestProductUrlColor = "batest-config4";
	//String payment = "Check/Mo"; //"Card";
	//By userName = By.name("login[username]");
	//By passWord = By.name("login[password]");
	//String simpleAddToCartSelector = "#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button";
	//By simpleAddToCart = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button");
	//String viewCartSelector = "body > div.fancybox-wrap.fancybox-desktop.fancybox-type-html.minicart-wrap.fancybox-opened > div > div > div > div.cart.display-single-price > form > div.minicart-bottom > div.edit-bag > a";
	//By viewCart = By.cssSelector("body > div.fancybox-wrap.fancybox-desktop.fancybox-type-html.minicart-wrap.fancybox-opened > div > div > div > div.cart.display-single-price > form > div.minicart-bottom > div.edit-bag > a");
	//By configAddToCart = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button");
	//String proceedToCheckoutButtonSelector = "body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.page-title.title-buttons > ul > li > button:nth-child(1)";
	//By proceedToCheckoutButton = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div > div.page-title.title-buttons > ul > li > button:nth-child(1)");
	//By chekoutLoginButton = By.cssSelector("#checkout-step-login > div > div.col-2 > div > button");
	//String shippingMethodSelector = "#checkout-shipping-method-load > dl > dd > ul > li.block_s_method_ups_GND > label";
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToSimpleTestProductPage(String url, String urlKey) {
		System.out.println("Go to " + url + urlKey);
		visit(url + urlKey);
	}
	
	//public void goToColorConfigTestProductPage() {
		//visit(configTestProductUrlColor);
	//}
	
	public Boolean simpleAddToCartButtonDisplayed(String simpleAddToCartSelector, String emailPopUp) {
		if ( waitForIsDisplayed(By.cssSelector(emailPopUp),10)){
			driver.findElement(By.cssSelector(emailPopUp)).click();
		}
		return waitForIsDisplayed(By. cssSelector(simpleAddToCartSelector),20);
	}
	public Header addSimpleToCart(String simpleAddToCartSelector) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('" + simpleAddToCartSelector + "').click()");
		//click(simpleAddToCart);
		return new Header(driver);
	}
	public Header viewCart(String viewCartSelector) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('" + viewCartSelector + "').click()");
		//click(viewCart);
		return new Header(driver);
	}
	
	public Checkout proceedToCheckout(String proceedToCheckoutButtonSelector, String productPopUp) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.querySelector('" + proceedToCheckoutButtonSelector + "').click()");
		if ( waitForIsDisplayed(By. cssSelector(productPopUp),10)){
			driver.findElement(By.cssSelector(productPopUp)).click();
		}
		//click(viewCart);
		return new Checkout(driver);
	}
	public Checkout proceedToCheckoutLogin(String user, String pass, String chekoutLoginButton, String userNameName, String passWordName) {
		type(user,By.name(userNameName));
		type(pass, By.name(passWordName));
		click(By.cssSelector(chekoutLoginButton));
		return new Checkout(driver);
	}
	public Checkout checkoutShippingInfo(String billingButton) {
		driver.findElement(By.cssSelector("#billing-address-select > option:nth-child(2)")).click();
		driver.findElement(By.id("billing:firstname")).clear();
		driver.findElement(By.id("billing:firstname")).sendKeys("Nick");
		driver.findElement(By.id("billing:lastname")).clear();
        driver.findElement(By.id("billing:lastname")).sendKeys("Ata");
        driver.findElement(By.id("billing:company")).clear();
        driver.findElement(By.id("billing:company")).sendKeys("Blue Acorn");
        driver.findElement(By.id("billing:street1")).clear();
        driver.findElement(By.id("billing:street1")).sendKeys("148 Williman Street");
        driver.findElement(By.id("billing:city")).clear();
        driver.findElement(By.id("billing:city")).sendKeys("Charleston");
        //Dropdown
        driver.findElement(By.cssSelector("#billing\\3a region_id > option:nth-child(42)")).click();
        driver.findElement(By.id("billing:region_id")).sendKeys("South Carolina");
        driver.findElement(By.id("billing:postcode")).clear();
        driver.findElement(By.id("billing:postcode")).sendKeys("29403");
        //Dropdown preselected
        driver.findElement(By.id("billing:telephone")).clear();
        driver.findElement(By.id("billing:telephone")).sendKeys("1234567890");
        driver.findElement(By.cssSelector(billingButton)).click();
		return new Checkout(driver);
	}
	public Checkout checkoutShippingMethod(String shippingMethodSelector, String shippingButton) {
		if ( waitForIsDisplayed(By.cssSelector(shippingMethodSelector),10)){
	        JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("document.querySelector('" + shippingMethodSelector + "').click()");
			driver.findElement(By.cssSelector(shippingButton)).click();
		};
		return new Checkout(driver);
	}
	public Checkout checkoutPaymentMethod(String payment) {
		if ( payment.equals("Card") ) {
			driver.findElement(By.cssSelector("#checkout-payment-method-load > dt:nth-child(1) > label")).click();
			driver.findElement(By.id("braintree_cc_number")).sendKeys("4242424242424242");
	        driver.findElement(By.id("braintree_cc_cid")).sendKeys("123");
	        driver.findElement(By.cssSelector("#braintree_expiration > option:nth-child(3)")).click();
	        driver.findElement(By.cssSelector("#braintree_expiration_yr > option:nth-child(4)")).click();
		}
		if ( payment.equals("Check/Mo") ) {
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
