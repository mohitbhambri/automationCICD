package seleniumframeworklearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworklearning.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']") 
	WebElement errMesg;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userMail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForElementToAppearByElement(errMesg, 3);
		return errMesg.getText();
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
}
