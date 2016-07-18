package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.Global;

public class Homepage extends Base {

	String homepageString = "";
	By homepageStringSelector;
	
	public Homepage(WebDriver driver) {
		super(driver);
		setHomepageString();
		setHomepageStringSelector();
	}
	
	public void goToHomepage() {
		visit("");
	}
	
	private void setHomepageString() {
		homepageString = Global.getHomepageString();
	}
	
	private void setHomepageStringSelector() {
		homepageStringSelector = By.cssSelector(Global.getHomepageStringSelector());
	}
	
	public Boolean homepageStringDisplayed() {
		return waitForIsDisplayed(homepageStringSelector);
	}
	
	public String getHomepageStringText() {
		return getText(homepageStringSelector);
	}
	
	public String getHomepageString() {
		return homepageString;
	}

}
