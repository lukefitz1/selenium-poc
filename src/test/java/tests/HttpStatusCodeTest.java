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
import pos.Homepage;
import utilities.HttpResponseCode;

public class HttpStatusCodeTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private HttpResponseCode http;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		
		driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(groups = { "functional" }, priority=0)
	public void goToHomepage() {
		hp = new Homepage(driver);
		http = new HttpResponseCode(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void httpStatusCheck() {
		Assert.assertEquals(http.checkHttpResponseCode(driver.getCurrentUrl()), 200, "Response code is a 200!");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
