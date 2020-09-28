package se.telenor.product.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
public class ProductWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;

	/**
	 * @return the products
	 */
	@JsonProperty(value = "data")
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
