package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;
import org.testng.ITestContext;

import pos.MyAccount;

public class AccountLoginTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private MyAccount acct;
	private String[] baseUrls;
	private String url;
	private String siteEnv;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "base_urls", "env","pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, String base_urls, String env, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		baseUrls = base_urls.split(",");
		siteEnv = new String(env);

		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	private int param;
 	
	    @Factory(dataProvider = "dataMethod")
	    public AccountLoginTest(int param) {
	        this.param = param;

	    }

	    @DataProvider(name = "usesParameter")
	    public static Object[][] dataMethod(ITestContext context) {
	    	String base_urls = context.getCurrentXmlTest().getParameter("base_urls");
	    	String[] urls = base_urls.split(",");
	    	switch (urls.length) {
	    		case 1: 
	    			return new Object[][] { { 0 }};
	    		case 2: 
	    			return new Object[][] { { 0  }, { 1 }};
	        	case 3: 
	        		return new Object[][] { { 0 }, { 1 }, { 2 }};
	    		default: 
	    			return new Object[][] { { 0 }};
	    	}
	    }
	
	@Test(groups = { "login" }, priority=0)
	public void goToLoginPage() {
		url = "http://" + siteEnv + "." + baseUrls[param];
		System.out.println("Checking "+url+" ...");
		acct = new MyAccount(driver);
		acct.goToLogin(url);
	}
	
	@Test(groups = { "login" }, priority=0)
	public void loginFormCheck() {
		Assert.assertTrue(acct.loginFormDisplayed(), "Login / register form is not displayed");
	}
	
	@Test(groups = { "login" }, priority=1)
	public void login() {
		acct.fillLoginForm("fet_lc_rm@blueacorn.com", "pass4fet_lc_rm");
		acct.clickLoginButton();
		Assert.assertTrue(acct.acctDashboardDisplayed(), "Account dashboard is not displayed");
	}
	
	@Test(groups = { "login" }, priority=2) 
	public void testLoginGreeting() {
		Assert.assertEquals(acct.getDashGreeting(), "HELLO, LOGIN CHECK BLUE ACORN INC.!");
	}	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
