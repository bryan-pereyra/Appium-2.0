package org.coeqacourse;

import org.coeqacourse.TestUtils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class eCommerce_TC_01 extends BaseTest {

	@BeforeMethod
	public void preSetup() {
		// Screen to Home Page
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}

	@Test
	public void FillForm_ErrorValidation() throws InterruptedException {
		// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("My
		// Name");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Canada']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
	}

	@Test
	public void FillForm_PositiveFlow() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("My Name");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Canada']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast")).size() < 1);
	}
}
