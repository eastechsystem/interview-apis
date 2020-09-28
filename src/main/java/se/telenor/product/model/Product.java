package se.telenor.product.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	private String property;
	private Float price;
	private String storeAddress;
	private Property productProperty;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the property
	 */
	@JsonProperty(value = "properties")
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the price
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return the storeAddress
	 */
	@JsonProperty(value = "store_address")
	public String getStoreAddress() {
		return storeAddress;
	}

	/**
	 * @param storeAddress the storeAddress to set
	 */
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	/**
	 * @return the productProperty
	 */
	@JsonIgnore
	public Property getProductProperty() {
		return productProperty;
	}

	/**
	 * @param productProperty the productProperty to set
	 */
	public void setProductProperty(Property productProperty) {
		this.productProperty = productProperty;
	}
}
