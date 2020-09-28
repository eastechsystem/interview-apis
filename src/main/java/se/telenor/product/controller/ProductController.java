package se.telenor.product.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import se.telenor.product.config.ApplicationConfig;
import se.telenor.product.model.Product;
import se.telenor.product.model.ProductWrapper;
import se.telenor.product.service.ProductService;
import se.telenor.product.util.Constant;

/**
 * ProductController is rest controller to define APIs related to products.
 *
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
@RestController
@RequestMapping(path = "/api/v1.0")
@Tag(name = Constant.PRODUCT_OPERATION_TAG, description = Constant.PRODUCT_OPEN_APIS_DESCRIPTION)
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;
	public static ApplicationConfig applicationConfig;
	
	/**
     * @param customFileProperties
     */
    @Autowired
    public void setApplicationConfig(ApplicationConfig applicationConfig) {
    	ProductController.applicationConfig = applicationConfig;
    }
	/**
	 * @param productService
	 */
	@Autowired
	public void setProductService(ProductService fileReaderServices) {
		this.productService = fileReaderServices;
	}

	/**
	 * getAllProductsDetails() This resource use to fetch product list with optional
	 * filters. i.e. type, max_price, city, property etc.
	 * 
	 * @return ResponseEntity
	 */
	// Open-APIs 3.0 Swagger documentation for rest-apis 
	@Operation(summary = Constant.PRODUCT_OPERATION_SUMMARY, description = Constant.PRODUCT_OPERATION_DESCRIPTION, tags = {Constant.PRODUCT_OPERATION_TAG})
	
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductWrapper> getAllProductsDetails(
			@RequestParam(required = false, name = "type") String type,
			@RequestParam(required = false, name = "min_price") Number minPrice,
			@RequestParam(required = false, name = "max_price") Number maxPrice,
			@RequestParam(required = false, name = "city") String city,
			@RequestParam(required = false, name = "property") String property,
			@RequestParam(required = false, name = "gb_limit_min") Number gbLimitMin,
			@RequestParam(required = false, name = "gb_limit_max") Number gbLimitMax) {

		LOGGER.info("getAllProductsDetails() Webservice starts!");

		ProductWrapper response = new ProductWrapper();
		HttpStatus httpStatus = null;
		try {
			List<Product> products = getFilterProductsByQueryParams(type, minPrice, maxPrice, city, property,
					gbLimitMin, gbLimitMax);
			httpStatus = (products == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
			response.setProducts(products);
		} catch (Exception e) {
			LOGGER.error("getAllProductsDetails() failed to execute method!", e);
		}

		LOGGER.info("getAllProductsDetails() Webservice ends!");
		return new ResponseEntity<ProductWrapper>(response, httpStatus);
	}

	/**
	 * getFilterProductsByQueryParams() method is use to filter product list by
	 * queries parameters and that list's data mapped from CSV file.
	 * 
	 * @param type
	 * @param minPrice
	 * @param maxPrice
	 * @param city
	 * @param property
	 * @param gbLimitMin
	 * @param gbLimitMax
	 * @return products of type {@link Product}
	 * @throws Exception
	 */
	private List<Product> getFilterProductsByQueryParams(String type, Number minPrice, Number maxPrice,
			String city, String property, Number gbLimitMin, Number gbLimitMax) throws Exception {
		List<Product> products = this.productService.getAllProducts();
		
		// collection filtering by queries parameters using Stream API
		Stream<Product> streams = products.stream();
		if (null != type) {
			streams = streams.filter(product -> product.getType().equals(type));
		}
		
		// filer by the color as property
		if (null != property && Constant.PRODUCT_PROPERTY_COLOR.equals(property)) {
			streams = streams.filter(product -> product.getProperty().contains(property));
		}
		
		// filer by the gb_limit as property
		if (null != property && Constant.PRODUCT_PROPERTY_GB_LIMIT.equals(property)) {
			streams = streams.filter(product -> product.getProperty().contains(property));
		}
		
		if (null != city) {
			streams = streams.filter(product -> product.getStoreAddress().contains(city));
		}
		
		if (null != gbLimitMin) {
			streams = streams.filter(product -> null != product.getProductProperty().getGbLimit() && product.getProductProperty().getGbLimit().intValue() >= gbLimitMin.intValue());
		}
		
		if (null != gbLimitMax) {
			streams = streams.filter(product -> null != product.getProductProperty().getGbLimit() && product.getProductProperty().getGbLimit().intValue() <= gbLimitMax.intValue());
		}
		
		if (null != minPrice) {
			streams = streams.filter(product -> product.getPrice().floatValue() >= minPrice.floatValue());
		}
		
		if (null != maxPrice) {
			streams = streams.filter(product -> product.getPrice().floatValue() <= maxPrice.floatValue());
		}
		
		products = streams.collect(Collectors.toList());
		
		return products;
	}
}
