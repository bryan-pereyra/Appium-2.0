package org.coeqacourse.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.coeqacourse.pageObjects.android.FormPage;
import org.coeqacourse.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException {

		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\org\\coeqacourse\\resources\\data.properties");

		properties.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: properties.getProperty("ipAddress");
		// String ipAddress = properties.getProperty("ipAddress");
		String port = properties.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// Start App
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(properties.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\coeqacourse\\resources\\chromedriver.exe");
		options.setApp(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\coeqacourse\\resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wait for elements to be visible
		formPage = new FormPage(driver);
	}

	public void longPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2500));
	}

	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Stop server
		driver.quit();
		service.stop();
	}

}
