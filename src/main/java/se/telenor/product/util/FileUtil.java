package se.telenor.product.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import se.telenor.product.controller.ProductController;
import se.telenor.product.model.Product;
import se.telenor.product.model.Property;

/**
 * FileUtil class is use to read CSV file's data and populate product collections
 * with that data.
 * 
 * @author jahanzaib.ali
 * @since September 26, 2020
 *
 */
public final class FileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	private static List<Product> products = new ArrayList<Product>(0);
	static {
		try {
			LOGGER.info("FileUtil -> static-block execution starts!");
			initProducts();
		} catch (Exception e) {
			LOGGER.error("FileUtil class static block failed to execute!", e);
		}
	}

	/**
	 * getPopulatedProducts() method use to return populated list of products.
	 * 
	 * @return products
	 * @throws Exception
	 */
	public static List<Product> getPopulatedProducts() throws Exception {
		if (null == products || products.isEmpty()) {
			initProducts();
		} 
		
		return products;
	}

	/**
	 * initProducts() method is use to initialize product's list with CSV file Data.
	 * 
	 * @throws Exception
	 */
	private static void initProducts() throws Exception {
		LOGGER.info("initProducts() method invoking starts!");

		try {
			populateProductsFromCsvDataFile();
		} catch (Exception e) {
			LOGGER.error("initProducts() method failed to execute!", e);
			throw e;
		}

		LOGGER.info("initProducts() method invoking ends!");
	}

	/**
	 * populateProductsFromCsvDataFile() method is use to populate product's list.
	 * 
	 * @throws NullPointerException
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	private static void populateProductsFromCsvDataFile()
			throws NullPointerException, NumberFormatException, IOException, URISyntaxException {
		LOGGER.info("populateProductsFromCsvDataFile() method invoking ends!");

		try {
			List<List<String>> csvDataList = readDataFromCsvFile();

			for (List<String> dataRow : csvDataList) {
				String type =  dataRow.get(0);
				String property =  dataRow.get(1);
				Float price = Float.parseFloat(dataRow.get(2));
				String storeAddress = dataRow.get(3)+ "," +  dataRow.get(4);
				storeAddress = storeAddress.replaceAll("\"", "");

				Product product = new Product();
				product.setType(type);
				product.setProperty(property);
				product.setPrice(price);
				product.setStoreAddress(storeAddress);
				
				Property productProperty = new Property();
				String[] arr = property.split(":");
				if (null != arr && Constant.PRODUCT_PROPERTY_GB_LIMIT.equalsIgnoreCase(arr[0])) {
					productProperty.setGbLimit(Integer.parseInt(arr[1]));
				} else if (null != arr && Constant.PRODUCT_PROPERTY_COLOR.equalsIgnoreCase(arr[0])) {
					productProperty.setColor(arr[1]);
				}
				
				product.setProductProperty(productProperty);
				products.add(product);
			}
		} catch (NullPointerException | NumberFormatException | IOException e) {
			LOGGER.error("populateProductsFromCsvDataFile() method failed to execute!", e);
			throw e;
		}

		LOGGER.info("populateProductsFromCsvDataFile() method invoking ends!");
	}

	/**
	 * readDataFromCsvFile() method is use to read and transform CSV file's data
	 * into Collections.
	 * 
	 * @return csvDataList
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	private static List<List<String>> readDataFromCsvFile() throws IOException, URISyntaxException {
		
		List<String> lines = Collections.emptyList();
		lines = getCsvFileDataFromConfiguredFilePath();
		List<List<String>> csvDataList = lines.stream().skip(1).map(line -> Arrays.asList(line.split(",")))
				.collect(Collectors.toList());

		return csvDataList;
	}

	/**
	 * getCsvFileDataFromConfiguredFilePath() method use to return CSV data file
	 * path.
	 * 
	 * @return csvFileContentList
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static  List<String> getCsvFileDataFromConfiguredFilePath() throws IOException, URISyntaxException {

		List<String> csvFileContentList = Collections.emptyList();
		try {
			Path csvDataFilePath = Paths.get(ProductController.applicationConfig.getLocalCsvFilePath());
			csvFileContentList = Files.readAllLines(csvDataFilePath, StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOGGER.error("############ Error to load CSV File Data #############" + e.getMessage());
			LOGGER.error("############ To load dynamic data, set the file path in application.properties #############");
		}

		// default file path if server local path doesn't exist
		if (null == csvFileContentList || csvFileContentList.isEmpty()) {
			LOGGER.info("######## Default CSV File Data loaded from class path ########");
			csvFileContentList = readCsvFileDataFromClassPath();
		}
		
		return csvFileContentList;
	}
	
	/**
	 * @return result
	 * @throws IOException
	 */
	private static List<String> readCsvFileDataFromClassPath() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("data.csv");
		InputStream inputStream = classPathResource.getInputStream();
		List<String> result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
		
		return result;
	}
}
