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
import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;
import org.testng.ITestContext;

public class HeaderVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	private String os;
	private String browser;
	private String version;
	private String device;
	private int width;
	private int height;
	private String[] baseUrls;
	private String url;
	private String siteEnv;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "base_urls", "env", "pwidth", "pheight", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, String base_urls, String env, @Optional("optional value") int pwidth, @Optional("optional value") int pheight, @Optional("optional value") String pdevice) throws MalformedURLException {		
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
		baseUrls = base_urls.split(",");
		siteEnv = new String(env);
		
		driver = new RemoteWebDriver(new URL(purl), capabilities);
		driver.manage().window().setSize(new Dimension(pwidth, pheight));
	}

	private int param;
 	
    @Factory(dataProvider = "usesParameter")
    public HeaderVisualTest(int param) {
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
    			return new Object[][] { { 0 }, { 1 }};
        	case 3: 
        		return new Object[][] { { 0 }, { 1 }, { 2 }};
    		default: 
    			return new Object[][] { { 0 }};
    	}
    }
	
	@Test(alwaysRun = true, groups = { "visual" }, priority = 0)
	public void eyesSetup() {
		System.out.println("Runing HeaderVisualTest...");
		url = "http://"  + siteEnv + "." +baseUrls[param];
		System.out.println("Checking "+url+" ...");
		driver = eyes.open(driver, "Base header visual tests", "Base header tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
	}
	
	@Test(alwaysRun = true, groups = { "visual" }, priority = 1)
	public void goToHomepage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage(url);
	}
	
	@Test(groups = { "visual" }, priority = 2)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header is not displayed");
	}
	
	@Test(groups = { "visual" }, priority = 3)
	public void headerScreenshot() {
		WebElement e = driver.findElement(header.getHeader());
		this.checkRegWithShift(eyes, e, 0, "Header screenshot on homepage");
	}
	
	@Test(alwaysRun = true, groups = { "visual" }, priority = 4)
	public void screenshotCompare() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
