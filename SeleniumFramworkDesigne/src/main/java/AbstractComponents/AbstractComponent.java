package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.xpath("//ul //button[@type='button']")).click();
	@FindBy(xpath = "//ul //i[@class='fa fa-shopping-cart']")
	WebElement cartIcon;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersIcon;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

	}

	public void waitForWebElementToAppear(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitForElementToDisappear(WebElement ele) {
		// Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf((ele)));

	}

	public void goToCart() {
		cartIcon.click();

	}
	public void goToMyOrders() {
		OrdersIcon.click();
	}

	public void scrollThePage(int x, int y) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
	}

}
