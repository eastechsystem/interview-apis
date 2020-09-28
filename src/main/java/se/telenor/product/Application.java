package se.telenor.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import se.telenor.product.config.ApplicationConfig;

/**
 * Application execution/entry point.
 * 
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
