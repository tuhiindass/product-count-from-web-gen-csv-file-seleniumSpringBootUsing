package io.cloudcraftz.chiron.service;

import io.cloudcraftz.chiron.dao.ProductPage2;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.cloudcraftz.chiron.dao.ProductPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TruHrtServiceImpl implements TruHrtService {
    private final boolean isDebugEnabled = log.isDebugEnabled();

    @Autowired
    private WebDriver webDriver;

    @Value("${application.url}")
    private String applicationUrl;

    @Value("${application.url2}")
    private String applicationUrl2;

    @Autowired
    private ProductPage productPage;

    @Autowired
    private ProductPage2 productPage2;

    public void automateProduct() throws Exception {
        // TODO Auto-generated method stub

        //clear cookies
        webDriver.manage().deleteAllCookies();

        // open webpage
        webDriver.get(applicationUrl);
        if (isDebugEnabled) {
            log.debug("Inside TruHrt Product page now.");
        }
        Thread.sleep(3000);

        productPage.getProduct();
        if (isDebugEnabled) {
            log.debug("success....");
        }

        webDriver.manage().deleteAllCookies();

        // open webpage
        webDriver.get(applicationUrl2);
        if (isDebugEnabled) {
            log.debug("Inside Gypsupp Product page now.");
        }
        Thread.sleep(3000);

        productPage2.getProduct();
        if (isDebugEnabled) {
            log.debug(" Process success....");
        }

    }

}
