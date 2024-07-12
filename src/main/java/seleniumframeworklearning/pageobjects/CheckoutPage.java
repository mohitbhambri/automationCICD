package seleniumframeworklearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement selCountryAction;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	private WebElement countrySelected;
	
	@FindBy(className="action__submit")
	private WebElement submitBtn;
		
	By countryResults = By.cssSelector(".ta-results");
	
	public void selectCountry(String country) {
		Actions a= new Actions(driver);
		a.sendKeys(selCountryAction, country).build().perform();
		waitForElementToAppear(countryResults, 3);
		countrySelected.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}
	
}
