
package se.telenor.product.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ProductControllerTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerTests.class);
	
	private static final String SERVER_ADDRESS = "http://localhost:";
	private static final String BASE_URL = "/api/v1.0";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testProductListAPI() throws Exception {
		printTestCaseNumberAsString(2);
		String operationName = "products";
		String queryParameters = "property=color";
		String endPoints = BASE_URL + "/" + operationName + "?" + queryParameters;
		
		ResponseEntity<String> entity = restTemplate.getForEntity(SERVER_ADDRESS + this.port + endPoints, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	
	private void printTestCaseNumberAsString(int testCaseNumber) {
		LOGGER.info("###############################################################");
		LOGGER.info("######################## Test Case - " + testCaseNumber + " ########################");
		LOGGER.info("###############################################################");
	}
}
