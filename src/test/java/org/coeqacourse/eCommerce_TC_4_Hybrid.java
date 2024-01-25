package org.coeqacourse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.coeqacourse.TestUtils.BaseTest;
import org.coeqacourse.pageObjects.android.CartPage;
import org.coeqacourse.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;

public class eCommerce_TC_4_Hybrid extends BaseTest {

	ExtentReports extent;

	@Test(dataProvider = "getData", groups = { "Smoke" })
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		double totalSum = cartPage.getProductSum();
		double displayedFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayedFormattedSum);
		cartPage.AcceptTermsConditions();
		cartPage.submitOrder();
	}

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\coeqacourse\\testData\\eCommerce.json");
		return new Object[][] { { data.get(0) } };
	}
}
