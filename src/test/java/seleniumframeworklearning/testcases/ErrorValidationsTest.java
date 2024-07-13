package seleniumframeworklearning.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumframeworklearning.testcomponents.BaseTest;
import seleniumframeworklearning.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		landingPage.loginApplication("mohitbhambri0@gmail.com", "Neh@1994");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

}
