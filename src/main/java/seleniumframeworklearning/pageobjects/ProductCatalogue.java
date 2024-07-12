package seleniumframeworklearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(className="ng-animating")
	WebElement spinner;
	
	By prodBy = By.cssSelector(".mb-3");
	By prodNameBy = By.cssSelector("b");
	By addToCartBy = By.cssSelector(".btn.w-10");
	By toastMessageBy = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(prodBy, 10);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		
		return getProductList().stream().filter(product -> 
		product.findElement(prodNameBy).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartBy).click();
		waitForElementToAppear(toastMessageBy, 5);
		waitForElementToDisappearByElement(spinner, 5);
	}
	
}
