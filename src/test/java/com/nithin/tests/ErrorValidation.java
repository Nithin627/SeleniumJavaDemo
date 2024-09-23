package com.nithin.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.ProductCatalog;
import com.nithin.testComponenets.BaseTest;

public class ErrorValidation extends BaseTest {
	@Test(groups = "ErrorHandling")
	public void LoginErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		String country = "India";

		ProductCatalog productCatalogue = landingpage.enterUserDetails("batman2@gmail.com", "Batman123@");
		String errorMsg = landingpage.getErrorMsg();
		Assert.assertEquals("Incorrect email or password.", errorMsg);

	}

	@Test(groups = "productValiadtion")
	public void productErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 32";
		String country = "India";

		ProductCatalog productCatalogue = landingpage.enterUserDetails("batman@gmail.com", "Batman123@");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean verifyProductDisplay = cartPage.verifyProductDisplay(productName);
		Assert.assertFalse(verifyProductDisplay);

	}

}
