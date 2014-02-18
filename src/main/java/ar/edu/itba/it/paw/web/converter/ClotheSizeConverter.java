package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.products.ClotheSize;
import ar.edu.itba.it.paw.domain.products.ClotheSizeRepo;

@Component
public class ClotheSizeConverter implements Converter<String, ClotheSize> {

	private ClotheSizeRepo clotheSizeRepo;

	@Autowired
	public ClotheSizeConverter(ClotheSizeRepo clotheSizeRepo) {

		this.clotheSizeRepo = clotheSizeRepo;
	}

	@Override
	public ClotheSize convert(String arg) {
		return clotheSizeRepo.get(Integer.valueOf(arg));
	}
}
