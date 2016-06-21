package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends Base {

	String oneStepCheckoutUrl = "onestepcheckout/?___SID=U";
	By checkoutForm = By.cssSelector("#checkoutSteps");
	By shippingInfoForm = By.cssSelector("#co-billing-form");
	By shippingMethodForm = By.cssSelector("#billing-progress-opcheckout.complete");
	By paymentMethodForm = By.cssSelector("#shipping_method-progress-opcheckout.complete");
	By reviewOrderForm = By.cssSelector("#payment-progress-opcheckout.complete");
	By confirmationForm = By.cssSelector("body > div.wrapper > div > div.main-container.col1-layout > div > div > div.success-order-number");
	
	public Checkout(WebDriver driver) {
		super(driver);
	}

	public Boolean checkoutFormDisplayed() {
		return waitForIsDisplayed(checkoutForm, 10);
	}
	public Boolean shippingInfoFormDisplayed() {
		return waitForIsDisplayed(shippingInfoForm, 10);
	}
	public String getOneStepCheckoutUrl() {
		return oneStepCheckoutUrl;
	}
	public Boolean shippingMethodFormDisplayed() {
		return waitForIsDisplayed(shippingMethodForm, 10);
	}
	public Boolean paymentMethodFormDisplayed() {
		return waitForIsDisplayed(paymentMethodForm, 10);
	}
	public Boolean reviewOrderFormDisplayed() {
		return waitForIsDisplayed(reviewOrderForm, 10);
	}
	public Boolean confirmationFormDisplayed() {
		return waitForIsDisplayed(confirmationForm, 10);
	}
}
