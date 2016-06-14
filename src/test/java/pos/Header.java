package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Checkout;

public class Header extends Base {

	By header = By.cssSelector("#header");
	By miniCartIcon = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > div");
	By miniCartBlock = By.cssSelector("#header-cart");
	By miniCartBlockOpen = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > div > div.block.block-cart.skip-content.skip-active");
	By miniCartBlockClosed = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > div > div.block.block-cart.skip-content:not(.skip-active)");
	By miniCartCartButton = By.cssSelector("#header-cart > div.minicart-wrapper > div.minicart-actions > a");
	By miniCartCheckoutButton = By.cssSelector("#header-cart > div.minicart-wrapper > div.minicart-actions > ul > li > a");
	By miniCartSpinner = By.cssSelector("#header-cart > div.minicart-wrapper.loading");
	protected Checkout checkout;
	
	public Header(WebDriver driver) {
		super(driver);
	}
	
	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public By getHeader() {
		return header;
	}
	
	public By getMiniCartBlock() {
		return miniCartBlock;
	}
	
	public Boolean miniCartOpen() {
		return waitForIsDisplayed(miniCartBlockOpen, 10);
	}
	
	public Boolean miniCartClosed() {
		return waitForIsNotDisplayed(miniCartBlockOpen, 10);
	}
	
	public Boolean miniCartCartButtonDisplayed() {
		return waitForIsDisplayed(miniCartCartButton);
	}
	
	public Boolean miniCartCheckoutButtonDisplayed() {
		return waitForIsDisplayed(miniCartCheckoutButton);
	}
	
	public Boolean miniCartSpinnerDisplayed() {
		return waitForIsDisplayed(miniCartSpinner, 10);
	}
	
	public Boolean miniCartSpinnerNotDisplayed() {
		return waitForIsNotDisplayed(miniCartSpinner, 10);
	}
	
	public Checkout clickMiniCartCheckoutButton() {
		click(miniCartCheckoutButton);
		checkout = new Checkout(driver);
		return checkout;
	}

}

