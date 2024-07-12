package seleniumframeworklearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr.ng-star-inserted th")
	List<WebElement> orderIdList;

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean verifySingleProductOrderId(String orderId) {
		Boolean match = orderIdList.stream().anyMatch(order -> order.getText().equals(orderId));
		return match;
	}
	
	public Boolean verifySingleProductOrderName(String productName) {
		Boolean match = productNames.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));
		return match;
	}
}
