package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> productsNames;

	public boolean VerifyOrderDisplay(String productName) {
		boolean flag = false;
		for (int i = 0; i < productsNames.size(); i++) {
			String TextOfProducts = productsNames.get(i).getText();

			if (TextOfProducts.equalsIgnoreCase(productName)) {
				flag = true;

			} else {
				flag = false;
			}
		}
		return flag;
	}

}
