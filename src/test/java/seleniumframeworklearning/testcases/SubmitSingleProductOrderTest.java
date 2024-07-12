package seleniumframeworklearning.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumframeworklearning.pageobjects.CartPage;
import seleniumframeworklearning.pageobjects.CheckoutPage;
import seleniumframeworklearning.pageobjects.ConfirmationPage;
import seleniumframeworklearning.pageobjects.OrderPage;
import seleniumframeworklearning.pageobjects.ProductCatalogue;
import seleniumframeworklearning.testcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;



public class SubmitSingleProductOrderTest extends BaseTest{
	String cartProduct = "Zara Coat 3";
	
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void singleProductSubmitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		
		ProductCatalogue prodCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//ProductCatalogue Page
		prodCatalogue.addProductToCart(input.get("product"));
		
		CartPage cartPage = prodCatalogue.goToCartPage();	
		Assert.assertTrue(cartPage.validateProductDisplay(input.get("product")));
		
		CheckoutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("Ind");
		
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getOrderCompletionMessage().equalsIgnoreCase("Thankyou for the order."));
	}
	
	//To verify if product is displaying in orders page
	@Test(dependsOnMethods= {"singleProductSubmitOrderTest"})
	public void singleProductOrderHistoryTest() {
		
		ProductCatalogue prodCatalogue = landingPage.loginApplication("mohitbhambri2024@gmail.com", "Neh@1994");
		OrderPage orderPage = prodCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifySingleProductOrderName(cartProduct));
		
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		
////		Object[][] objectArray = new Object[2][3];
////		//1st set of data
////		objectArray[0][0]= "mohitbhambri2024@gmail.com";
////		objectArray[0][1]= "Neh@1994";
////		objectArray[0][2]= "Zara Coat 3";
////		
////		//2nd set of data
////		objectArray[1][0]= "mohitbhambri0622@gmail.com";
////		objectArray[1][1]= "Neh@1994";
////		objectArray[1][2]= "IPHONE 13 PRO";
//	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//seleniumframeworklearning//data//purchaseOrder.json");
		
		return new Object[][]{{data.get(0)}, {data.get(1)}};
	}
}
