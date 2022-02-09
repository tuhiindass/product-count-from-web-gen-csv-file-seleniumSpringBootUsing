package io.cloudcraftz.chiron.controller;

import io.cloudcraftz.chiron.dao.ProductPage2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudcraftz.chiron.dao.ProductPage;
import io.cloudcraftz.chiron.service.TruHrtService;
import io.cloudcraftz.chiron.vo.ProductOrderResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */

@Slf4j
@RestController
public class APIController {
	private final boolean isDebugEnabled = log.isDebugEnabled();


	@Autowired
	private TruHrtService truHrtService;

	@Autowired
	private ProductPage productPage;

	@Autowired
	private ProductPage2 productPage2;

	@GetMapping(path = "/ping")
	public String ping() {
		if(isDebugEnabled) {
			log.debug("Yes, I am alive.");
		}
		return "ALIVE";
	}

	@GetMapping(path = "/productFind")
	public ResponseEntity<ProductOrderResponse> automateProduct() {
		ProductOrderResponse response = new ProductOrderResponse();
		try {
			truHrtService.automateProduct();
			response.setStatus("SUCCESS");
			response.setTruHrtData(productPage.TruHrtProductNames);
			response.setGypsuppData(productPage2.GymsuppProductNames);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception ex) {
			log.error("Error in placing the order", ex);
			response.setStatus("ERROR");

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
