package io.cloudcraftz.chiron.dao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;

	@Value("${application.productPage.productSearch.xpath}")
	private String productSearchXpath;



	public List<String> TruHrtProductNames =new ArrayList<>() ;

	public int count = 0;

	public void getProduct() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> products = webDriver.findElements(By.xpath(productSearchXpath));
		if (isDebugEnabled) {
			log.debug("Product Search Link has been clicked.");
		}

		for (WebElement product : products) {
			TruHrtProductNames.add(product.getText());
			System.out.println(product.getText());
		}

	}

}
