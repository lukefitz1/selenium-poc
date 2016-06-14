package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends Base {

	String oneStepCheckoutUrl = "onestepcheckout/?___SID=U";
	By checkoutForm = By.cssSelector("#onestepcheckout-form");
	
	public Checkout(WebDriver driver) {
		super(driver);
	}

	public Boolean checkoutFormDisplayed() {
		return waitForIsDisplayed(checkoutForm, 10);
	}
	
	public String getOneStepCheckoutUrl() {
		return oneStepCheckoutUrl;
	}
}
