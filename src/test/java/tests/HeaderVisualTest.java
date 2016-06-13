package tests;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pos.Header;
import pos.Homepage;
import com.applitools.eyes.RectangleSize;

public class HeaderVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pwidth", "pheight", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, @Optional("optional value") int pwidth, @Optional("optional value") int pheight, @Optional("optional value") String pdevice) throws MalformedURLException {		
		width = pwidth;
		height = pheight;
		browser = pbrowser;
		version = pversion;
		os = pos;
		device = pdevice;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		
		driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}
	
	@Test(alwaysRun = true, groups = { "visual" }, priority=0)
	public void eyesSetup() {
		driver = eyes.open(driver, "Base header visual tests", "Base header tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
	}
	
	@Test(alwaysRun = true, groups = { "functional" }, priority=1)
	public void goToHomepage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header is not displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void headerScreenshot() {
		WebElement e = driver.findElement(header.getHeader());
		this.checkRegWithShift(eyes, e, 0, "Header screenshot on homepage");
	}
	
	@Test(alwaysRun = true, groups = { "functional" }, priority=9)
	public void screenshotCompare() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
