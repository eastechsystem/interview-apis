package se.telenor.product.util;

/**
 * Constant is final class is use to define all constants of the application.
 * 
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
public final class Constant {
	// Open-APIs 3.0 Configurations constants.
	public static final String OPEN_API_APP_TITLE = "Interview-APIs Application Documentation";
	public static final String OPEN_API_APP_DISCRIPTION = "This is a Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.0";

	// Constant use for swagger documentation in Product's Rest-Controller
	public static final String PRODUCT_OPEN_APIS_DESCRIPTION = "The Product APIs";
	public static final String PRODUCT_OPERATION_SUMMARY = "Fetch all products operation by optional filters.";
	public static final String PRODUCT_OPERATION_DESCRIPTION =  "This REST API endpoint is use to returns a filtered set of products from the provided data in the data.csv file" + 
			"\n<br/>" + 
			"<br/>GET /product\n" + 
			"<br/><strong>Query Parameter	 	</strong><span style='padding-left: 75px;'>	Description</span>" + 
			"<br/><strong>type				 	</strong><span style='padding-left: 158px;'>	The product type. (String. Can be 'phone' or 'subscription')</span>" + 
			"<br/><strong>min_price			 	</strong><span style='padding-left: 120px;'>	The minimum price in SEK. (Number</span>" + 
			"<br/><strong>max_price			 	</strong><span style='padding-left: 117px;'>	The maximum price in SEK. (Number)</span>" + 
			"<br/><strong>city				 	</strong><span style='padding-left: 162px;'>	The city in which a store is located. (String)</span>" + 
			"<br/><strong>property			 	</strong><span style='padding-left: 129px;'>	The name of the property. (String. Can be 'color' or 'gb_limit')</span>" + 
			"<br/><strong>property:color	 	</strong><span style='padding-left: 91px;'>	The color of the phone. (String)</span>" + 
			"<br/><strong>property:gb_limit_min </strong><span style='padding-left: 39px;'>  The minimum GB limit of the subscription. (Number)</span>" + 
			"<br/><strong>property:gb_limit_max </strong><span style='padding-left: 36px;'>  The maximum GB limit of the subscription. (Number)</span>";
	public static final String PRODUCT_OPERATION_TAG = "Products";
	
	// Product properties sub labels
	public static final String PRODUCT_PROPERTY_COLOR = "color";
	public static final String PRODUCT_PROPERTY_GB_LIMIT = "gb_limit";
}
