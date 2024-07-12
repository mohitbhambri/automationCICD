package seleniumframeworklearning.testcomponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframeworklearning.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src/main//java//seleniumframeworklearning//resources//GlobalData.Properties");
		prop.load(fis);
		
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			//WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
				options.addArguments("headless");
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));// run in full screen
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
		}
		//WebDriverManager.chromedriver().setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png";
		File file = new File(filePath);
		FileUtils.copyFile(source, file);
		return filePath;
		
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo("https://rahulshettyacademy.com/client");
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
