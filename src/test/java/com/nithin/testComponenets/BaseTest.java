package com.nithin.testComponenets;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.nithin.base.pageobject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver InitialiseDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\resourses\\global.properties");
		prop.load(file);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firfox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Enter browser");

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

//		WebDriverManager.chromedriver().setup();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = InitialiseDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	public void tearDown() {
		driver.close();
	}

}
