/**
 * 
 */
package se.telenor.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
@Configuration
@ConfigurationProperties(prefix = "custom.application.properties")
public class ApplicationConfig {
	private String localCsvFilePath;

	/**
	 * @return the localCsvFilePath
	 */
	public String getLocalCsvFilePath() {
		return localCsvFilePath;
	}

	/**
	 * @param localCsvFilePath the localCsvFilePath to set
	 */
	public void setLocalCsvFilePath(String localCsvFilePath) {
		this.localCsvFilePath = localCsvFilePath;
	}
	
	
}
