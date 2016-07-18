package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tests.Global;

public class Header extends Base {

	String searchBarStr = "search";
	By header = By.cssSelector("#header");
	By searchBar = By.cssSelector("#search");
	By searchButton = By.cssSelector("#search_mini_form > div.input-box > button");
	By miniCart = By.cssSelector("#header-cart");
	By miniCartCheckoutButton;
	By miniCartWrapper;
	
	public Header(WebDriver driver) {
		super(driver);
		getMiniCartWrapper();
		getMiniCartCheckoutButton();
	}

	private void getMiniCartWrapper() {
		miniCartWrapper = By.cssSelector(Global.getMiniCartWrapper());
	}
	
	private void getMiniCartCheckoutButton() {
		miniCartCheckoutButton = By.cssSelector(Global.getMiniCartCheckoutButton());
	}
	
	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public Boolean searchBarDisplayed() {
		return waitForIsDisplayed(searchBar, 10);
	}
	
	public By getSearchBar() {
		return searchBar;
	}
	
	public void searchFor(String searchTerm) {
		type(searchTerm, searchBar);
		WebElement e = driver.findElement(searchBar);
		e.submit();
	}
	
	public Boolean miniCartDisplayed() {
		return waitForIsDisplayed(miniCartWrapper);
	}
	
	public Boolean miniCartCheckoutButtonDisplayed() {
		return waitForIsDisplayed(miniCartCheckoutButton);
	}
	
	public void clickMiniCartCheckoutButton() {
		click(miniCartCheckoutButton);
	}
}
