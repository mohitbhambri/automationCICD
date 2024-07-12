package seleniumframeworklearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement greetingMessage;
	
	@FindBy(css="label.ng-star-inserted")
	WebElement orderId;
	
	public String getOrderCompletionMessage() {
		return greetingMessage.getText();
	}
	
	public String getSingleProductOrderId() {
		String orderNumber = orderId.getText().split("|")[0].trim();
		return orderNumber;
	}
	
}
