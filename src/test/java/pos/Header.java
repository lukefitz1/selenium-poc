package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends Base {

	By header = By.cssSelector("#header");
	
	public Header(WebDriver driver) {
		super(driver);
	}
	
	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public By getHeader() {
		return header;
	}
}

