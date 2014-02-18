package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductRepo;

@Component
public class ProductConverter implements Converter<String, Product> {

	private ProductRepo productRepo;

	@Autowired
	public ProductConverter(ProductRepo prodRepo) {

		this.productRepo = prodRepo;
	}

	@Override
	public Product convert(String arg) {
		return productRepo.get(Integer.valueOf(arg));
	}

}
