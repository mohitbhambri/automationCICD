package seleniumframeworklearning.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumframeworklearning.pageobjects.CartPage;
import seleniumframeworklearning.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement addToCartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersPageBtn;
	
	public void waitForElementToAppear(By findBy, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppearByElement(WebElement findElement, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(findElement));
	}
	
	public void waitForElementToDisappearByElement(WebElement findElement, int seconds) throws InterruptedException {
		Thread.sleep(4000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
//		wait.until(ExpectedConditions.invisibilityOf(findElement));
	}
	
	public CartPage goToCartPage() {
		addToCartBtn.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrdersPage() {
		ordersPageBtn.click();
		return new OrderPage(driver);
	}
	
	
}
