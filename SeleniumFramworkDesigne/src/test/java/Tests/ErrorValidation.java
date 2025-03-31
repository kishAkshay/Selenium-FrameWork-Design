package Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Cart;
import PageObject.LandingPage;
import PageObject.ProductListingPage;
import TestComponents.BaseTests;
import TestComponents.Retry;

public class ErrorValidation extends BaseTests {
	String productname = "IPHONE 13 PRO";
	@Test(groups = "ErrorHandle" ,retryAnalyzer=Retry.class)
	public void SubmiteOrder() throws IOException {
		BaseTests baseTest = new BaseTests();
		driver = baseTest.OpenTheBrowser();
		LandingPage landpage = new LandingPage(driver);
		landpage.goTo();
		landpage.loginApplication("user18@gmail.com","User@gmail.com");
		String errorMessageText = landpage.getTheerrorMessage();
		Assert.assertEquals("Incorrect email password.",errorMessageText);	
		
	}
	@Test
	public void VarifyProductAddToCart() throws IOException {
		
		BaseTests baseTest = new BaseTests();
		driver = baseTest.OpenTheBrowser();
		LandingPage landPage = new LandingPage(driver);
		landPage.goTo();
		landPage.loginApplication("user18@gmail.com","User18@gmail.com");
		ProductListingPage plp = new ProductListingPage(driver);
		List<WebElement> products = plp.getTheProducts();
		plp.getProductsByNameAndAddToCart(products);
		plp.goToCart();
		Cart cartPage = new Cart(driver);
		boolean value = cartPage.checkProductAddedTocard(productname);
		Assert.assertTrue(value);
	}
	
}
