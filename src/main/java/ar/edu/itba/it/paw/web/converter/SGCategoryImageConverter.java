package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.images.SGCategoryImage;
import ar.edu.itba.it.paw.domain.images.SGCategoryImageRepo;

@Component
public class SGCategoryImageConverter implements Converter<String,  SGCategoryImage> {

	private SGCategoryImageRepo sgImageCategoryRepo;

	@Autowired
	public SGCategoryImageConverter(SGCategoryImageRepo sgimageRepo) {

		this.sgImageCategoryRepo = sgimageRepo;
	}

	@Override
	public  SGCategoryImage convert(String arg) {
		return sgImageCategoryRepo.get(Integer.valueOf(arg));
	}

}
