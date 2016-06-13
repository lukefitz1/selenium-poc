package pos;

import org.openqa.selenium.WebDriver;

public class Homepage extends Base {

	public Homepage(WebDriver driver) {
		super(driver);
	}
	
	public void goToHomepage() {
		visit("");
	}
	
}
