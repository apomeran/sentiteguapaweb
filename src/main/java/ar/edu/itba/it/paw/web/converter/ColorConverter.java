package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.products.ProductColor;
import ar.edu.itba.it.paw.domain.products.ProductColorRepo;

@Component
public class ColorConverter implements Converter<String, ProductColor> {

	private ProductColorRepo prodcolorRepo;

	@Autowired
	public ColorConverter(ProductColorRepo prodColorRepo) {

		this.prodcolorRepo = prodColorRepo;
	}

	@Override
	public ProductColor convert(String arg) {
		return prodcolorRepo.get(Integer.valueOf(arg));
	}
}
