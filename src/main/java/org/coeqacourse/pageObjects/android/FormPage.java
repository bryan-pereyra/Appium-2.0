package org.coeqacourse.pageObjects.android;

import org.coeqacourse.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOptionRadioButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleOptionRadioButton;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelectionDropdown;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	// Methods
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("Female")) {
			femaleOptionRadioButton.click();
		} else {
			maleOptionRadioButton.click();
		}
	}

	public void setCountrySelection(String countryName) {
		countrySelectionDropdown.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
	}

	public ProductCatalogue submitForm() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}
}
