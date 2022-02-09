package io.cloudcraftz.chiron.dao;

import io.cloudcraftz.chiron.csv.file.CSVFileGen;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuhin
 *
 */
@Slf4j
@Component
public class ProductPage2 {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;

	@Value("${application.gymProductPage.password}")
	private String password;

	@Value("${application.gymProductPage.productSearch.xpath}")
	private String productSearchXpath;

	@Value("${application.gymProductPage.passwordField.xpath}")
	private String passwordFieldXpath;

	@Value("${application.gymProductPage.passwordSubmit.xpath}")
	private String passwordSubmitXpath;

	@Value("${application.gymProductPage.nextPage.xpath}")
	private String nextPageXpath;

	@Autowired
	private ProductPage productPage;

	@Autowired
	private CSVFileGen csvFileGen;

	public List<String> GymsuppProductNames = new ArrayList<String>();

	public void getProduct() throws InterruptedException {

		webDriver.findElement(By.xpath(passwordFieldXpath)).sendKeys(password);
		webDriver.findElement(By.xpath(passwordSubmitXpath)).click();
		 if (isDebugEnabled) {
             log.debug("Gypsupp Product page has been Login.");
         }
		Thread.sleep(3000);

		List<WebElement> products;

		products = webDriver.findElements(By.xpath(productSearchXpath));
		Thread.sleep(2000);

		for (WebElement product : products) {
			GymsuppProductNames.add(product.getText());
			System.out.println(product.getText());
		}
		webDriver.findElement(By.xpath(nextPageXpath)).click();
		Thread.sleep(2000);
		products = webDriver.findElements(By.xpath(productSearchXpath));

		for (WebElement product : products) {
			GymsuppProductNames.add(product.getText());
			System.out.println(product.getText());
		}
		String[] productPageAr = new String[productPage.TruHrtProductNames.size()];
		String[] productPage2Ar = new String[GymsuppProductNames.size()];

		List<String> listProductPage = productPage.TruHrtProductNames;
		listProductPage.toArray(productPageAr);

		List<String> listProductPage2 = GymsuppProductNames;
		listProductPage2.toArray(productPage2Ar);

		List<String[]> data = new ArrayList<String[]>();
		data.add(productPageAr);
		data.add(productPage2Ar);
		CSVFileGen.writeDataAtOnce(data);

	}

}
