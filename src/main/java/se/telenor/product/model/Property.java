package se.telenor.product.model;

import java.io.Serializable;

/**
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
public class Property implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String color;
	private Integer gbLimit;

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the gbLimit
	 */
	public Integer getGbLimit() {
		return gbLimit;
	}

	/**
	 * @param gbLimit the gbLimit to set
	 */
	public void setGbLimit(Integer gbLimit) {
		this.gbLimit = gbLimit;
	}
}
