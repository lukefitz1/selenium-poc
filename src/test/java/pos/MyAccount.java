package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccount extends Base {

	String acctLoginUrl = "customer/account/login/";
	String acctDashUrl = "customer/account/";
	By accountLoginForm = By.cssSelector("#login-form");
	By loginEmail = By.cssSelector("#email");
	By loginPw = By.cssSelector("#pass");
	By loginButton = By.cssSelector("#send2");
	By acctDashName = By.cssSelector("body > div.wrapper > div.page > div.main-container.col2-left-layout > div > div.col-main > div > div > div.welcome-msg > p.hello > strong");
	By acctDash = By.cssSelector("body > div.wrapper > div.page > div.main-container.col2-left-layout > div > div.col-main > div > div");
	
	public MyAccount(WebDriver driver) {
		super(driver);
	}
	
	public void goToLogin(String url) {
		visit(url + acctLoginUrl);
	}
	
	public Boolean loginFormDisplayed() {
		return waitForIsDisplayed(accountLoginForm, 10);
	}
	
	public Boolean loginEmailFieldDisplayed() {
		return waitForIsDisplayed(loginEmail, 10);
	}
	
	public Boolean loginPwFieldDisplayed() {
		return waitForIsDisplayed(loginPw, 10);
	}
	
	public Boolean acctDashboardDisplayed() {
		return waitForIsDisplayed(acctDash, 10);
	}

	public void fillLoginForm(String un, String pw) {
		type(un, loginEmail);
		type(pw, loginPw);	
	}
	
	public void clickLoginButton() {
		click(loginButton);
	}
	
	public String getAccountDashboardUrl() {
		return acctDashUrl;
	}
	
	public String getDashGreeting() {
		return getText(acctDashName);
	}
}
