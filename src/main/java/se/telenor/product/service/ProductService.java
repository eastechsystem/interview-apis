package se.telenor.product.service;

import java.util.List;

import se.telenor.product.model.Product;

/**
 * Product services for business use-cases implementations.
 *
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
public interface ProductService {

	/**
	 * getAllProducts() method use to get product's list by processing local CSV
	 * data file.
	 * 
	 * @return product's list type of {@link se.telenor.product.model.Product}
	 * @throws Exception
	 */
	List<Product> getAllProducts() throws Exception;

}
