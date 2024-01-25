package org.coeqacourse.pageObjects.android;

import java.util.List;

import org.coeqacourse.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Elements
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsBtn;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptBtn;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;

	// Methods
	public List<WebElement> getProductList() {
		return productList;
	}

	public double getProductSum() {
		int count = productList.size();
		double totalSum = 0;

		for (int i = 0; i < count; i++) {
			String amountString = productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;
		}
		return totalSum;
	}

	public Double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}

	public void AcceptTermsConditions() {
		longPressAction(termsBtn);
		acceptBtn.click();
	}

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	public void submitOrder() {
		checkbox.click();
		proceedBtn.click();
	}
}
