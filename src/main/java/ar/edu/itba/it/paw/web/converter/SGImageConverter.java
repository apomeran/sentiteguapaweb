package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.images.SGImage;
import ar.edu.itba.it.paw.domain.images.SGImageRepo;

@Component
public class SGImageConverter implements Converter<String, SGImage> {

	private SGImageRepo SGImageRepo;

	@Autowired
	public SGImageConverter(SGImageRepo sgimageRepo) {

		this.SGImageRepo = sgimageRepo;
	}

	@Override
	public SGImage convert(String arg) {
		return SGImageRepo.get(Integer.valueOf(arg));
	}

}
