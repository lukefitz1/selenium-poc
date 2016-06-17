package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pos.Header;

public class ProductPage extends Base {

	String simpleTestProductUrl = "ba-test";
	String configTestProductUrlColor = "batest-config4";
	By simpleAddToCart = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button");
	By configAddToCart = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button");
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToSimpleTestProductPage(String url) {
		visit(url + simpleTestProductUrl);
	}
	
	public void goToColorConfigTestProductPage() {
		visit(configTestProductUrlColor);
	}
	
	public Boolean simpleAddToCartButtonDisplayed() {
		return waitForIsDisplayed(simpleAddToCart);
	}
	
	public Header addSimpleToCart() {
		click(simpleAddToCart);
		return new Header(driver);
	}
}
