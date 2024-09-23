package com.nithin.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.CheckOutPage;
import com.nithin.base.pageobject.ConfirmationPage;
import com.nithin.base.pageobject.LandingPage;
import com.nithin.base.pageobject.OrderPage;
import com.nithin.base.pageobject.ProductCatalog;
import com.nithin.testComponenets.BaseTest;

public class SubmitOrderTest extends BaseTest {
//	String productName = "ZARA COAT 3";

	@Test(dataProvider = "testData",groups= {"Purchase"})
	public void submitOrder(String userName, String pass, String productName) throws InterruptedException, IOException {
//		String productName = "ZARA COAT 3";
		String country = "India";

//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();

//		LandingPage landingpage = new LandingPage(driver);

//		landingpage.goTo();

//		LandingPage landingpage = launchApplication();
		ProductCatalog productCatalogue = landingpage.enterUserDetails(userName, pass);

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean verifyProductDisplay = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(verifyProductDisplay);
		CheckOutPage checkOutPage = cartPage.goTocheckOut();
		checkOutPage.enterCountryDetails(country);
		ConfirmationPage confirMationPage = checkOutPage.submitOrder();

		String confirmMessage = confirMationPage.getConfimMSG();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "testData")
	public void orderHistoryTest(String userName, String pass, String productName) {
		ProductCatalog productCatalogue = landingpage.enterUserDetails(userName, pass);
		OrderPage orderpage = productCatalogue.goToOrderPage();

		Assert.assertTrue(orderpage.verifyOrederDisplay(productName));

	}

//	driving data form Json
	@DataProvider(name = "testData")
	public Object[][] getData() {

		return new Object[][] { { "batman@gmail.com", "Batman123@", "ZARA COAT 3" },
				{ "batman@gmail.com", "Batman123@", "ADIDAS ORIGINAL" } };

	}

}
