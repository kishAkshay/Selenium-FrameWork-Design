package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
		public WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			    //WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	public WebDriver OpenTheBrowser() throws IOException {
		driver = initializeDriver();
		return driver;
		
	}
	
		
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//report//" +testCaseName+ ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png";
		
	}

}
