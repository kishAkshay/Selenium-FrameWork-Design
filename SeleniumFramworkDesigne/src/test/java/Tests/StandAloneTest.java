package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.Cart;
import PageObject.CheckOutPage;
import PageObject.ConfirmationPage;
import PageObject.LandingPage;
import PageObject.OrderPage;
import PageObject.ProductListingPage;
import ReadDataFromJsonFile.DataReader;
import TestComponents.BaseTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTests {
	String productname = "IPHONE 13 PRO";

	@Test(dataProvider = "getData",groups = "purchase")
	public void submitOrder(HashMap<String, String>  input) throws IOException {

		BaseTests baseTest = new BaseTests();
		driver = baseTest.OpenTheBrowser();
		LandingPage landPage = new LandingPage(driver);
		landPage.goTo();
		landPage.loginApplication(input.get("email"),input.get("password"));
		ProductListingPage plp = new ProductListingPage(driver);
		List<WebElement> products = plp.getTheProducts();
		plp.getProductsByNameAndAddToCart(products);
		plp.goToCart();
		Cart cartPage = new Cart(driver);
		boolean value = cartPage.checkProductAddedTocard(productname);
		Assert.assertTrue(value);
		cartPage.clickOnCheckoutbutton();
		cartPage.scrollThePage(0, 500);
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.enterCountryNameInDropDownAndWaitForOptionAppear("india");
		checkOutPage.selectCountyNameFromOptions();
		checkOutPage.clickOnSubmitButton();
		ConfirmationPage ConfirmationMessage = new ConfirmationPage(driver);
		String OrderConfirmationText = ConfirmationMessage.getTheOrderSuccessmessg();
		Assert.assertTrue(OrderConfirmationText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() throws IOException {
		BaseTests baseTest = new BaseTests();
		driver = baseTest.OpenTheBrowser();
		LandingPage landPage = new LandingPage(driver);
		landPage.goTo();
		landPage.loginApplication("user18@gmail.com","User18@gmail.com");
		OrderPage orderpage = new OrderPage(driver);
		orderpage.goToMyOrders();
		boolean value = orderpage.VerifyOrderDisplay(productname);
		Assert.assertTrue(value);
	}
	@DataProvider
	public Object[][] getData() throws IOException {
	
		DataReader datareader = new DataReader();
		 List<HashMap<String, String>> data = datareader.getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ReadDataFromJsonFile\\PurchaseOrder.json");
		 return new Object[][] {{data.get(0)},{data.get(1)}};
	
		
	
	
//	@DataProvider
//	public Object[][]getData() {
//		Object[][] data = new Object[2][2];
//		data[0][0]="user18@gmail.com";
//		data[0][1]="User18@gmail.com";
//		
//		data[1][0]="anshika@gmail.com";
//		data[1][1]="Iamking@000";
//		
//		return data;
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "user18@gmail.com");
//		map.put("password", "User18@gmail.com");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "shetty@gmail.com");
//		map2.put("password", "Iamking@000");
//		return new Object[][] {
//			{map},
//			{map1},
//			{map2}
//		};
	
	}

}
