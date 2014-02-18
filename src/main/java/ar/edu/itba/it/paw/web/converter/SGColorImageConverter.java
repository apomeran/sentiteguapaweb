package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.images.SGColorImage;
import ar.edu.itba.it.paw.domain.images.SGColorImageRepo;

@Component
public class SGColorImageConverter implements Converter<String, SGColorImage> {

	private SGColorImageRepo sgColorImageRepo;

	@Autowired
	public SGColorImageConverter(SGColorImageRepo sgimageRepo) {

		this.sgColorImageRepo = sgimageRepo;
	}

	@Override
	public SGColorImage convert(String arg) {
		return sgColorImageRepo.get(Integer.valueOf(arg));
	}

}
