package seleniumframeworklearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProductTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
		
	public Boolean validateProductDisplay(String productName) {
		Boolean match = cartProductTitles.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
	
}
