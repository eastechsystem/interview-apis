package se.telenor.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.telenor.product.model.Product;
import se.telenor.product.util.FileUtil;

/**
 * Product services implementation for business use-cases.
 *
 * @author jahanzaib.ali
 * @since September 26, 2020
 * 
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getAllProducts() throws Exception {
		try {
			return FileUtil.getPopulatedProducts();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
