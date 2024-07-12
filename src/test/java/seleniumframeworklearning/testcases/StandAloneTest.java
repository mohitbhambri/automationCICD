package seleniumframeworklearning.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) {
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("mohitbhambri2024@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Neh@1994");
		
		
		driver.findElement(By.id("login")).click();		
		String cardProduct = "Zara Coat 3";
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.xpath("//h5/b")).getText().equalsIgnoreCase(cardProduct)).findFirst().orElse(null);
	
		prod.findElement(By.cssSelector(".btn.w-10")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartList = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartList.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(cardProduct));
		
		Assert.assertTrue(match);
	
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.className("action__submit")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
