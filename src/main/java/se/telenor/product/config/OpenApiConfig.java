package se.telenor.product.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import se.telenor.product.util.Constant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * OpenApiConfig class use to define the configuration of swagger Open-APIs 3.0
 * documentations for rest-APIs.
 * 
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title(Constant.OPEN_API_APP_TITLE).description(Constant.OPEN_API_APP_DISCRIPTION));
	}
}
