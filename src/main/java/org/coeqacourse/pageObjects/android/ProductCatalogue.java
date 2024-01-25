package org.coeqacourse.pageObjects.android;

import java.util.List;

import org.coeqacourse.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCartButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	// Methods
	public void addItemToCartByIndex(int index) {
		addToCartButton.get(index).click();
	}

	public CartPage goToCartPage() throws InterruptedException {
		cartButton.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}
