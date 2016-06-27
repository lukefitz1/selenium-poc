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
import pos.Homepage;
import utilities.HttpResponseCode;

public class HttpStatusCodeTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private HttpResponseCode http;
	private String[] baseUrls;
	private String url;
	private String siteEnv;


	@Parameters({"pbrowser", "pversion", "pos", "pdevice", "purl", "base_urls", "env","pwidth", "pheight"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String pdevice, String purl, String base_urls, String env, @Optional("optional value") int pwidth, @Optional("optional value") int pheight) throws MalformedURLException {		
		browser = pbrowser;
		capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(pbrowser);
		//capabilities.setDevice(pdevice);
		capabilities.setCapability("deviceName", pdevice);
		capabilities.setVersion(pversion);
		capabilities.setPlatform(setPlatform(pos));
		baseUrls = base_urls.split(",");
		siteEnv = new String(env);
		driver = new RemoteWebDriver(new URL(purl), capabilities);
		if ( !pos.equals("ios")) {
			driver.manage().window().setSize(new Dimension(pwidth, pheight));
		}
	}
	private int index;
	
    @Factory(dataProvider = "usesParameter")
    public HttpStatusCodeTest(int index) {
        this.index = index;
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

	@Test(groups = { "functional", "homepage" }, invocationCount = 1, threadPoolSize = 1, priority = 0)
	public void goToHomepage() {
		System.out.println("Runing HttpStatusCodeTest...");
		url = "http://"  + siteEnv + "." +baseUrls[index];
		System.out.println("Checking "+url+" ...");
		hp = new Homepage(driver);
		http = new HttpResponseCode(driver);
		hp.goToHomepage(url);
	}

	@Test(groups = { "functional", "homepage" }, priority = 1)
	public void httpStatusCheck() {
		Assert.assertEquals(http.checkHttpResponseCode(driver.getCurrentUrl()), 200, "Response code is a 200!");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
