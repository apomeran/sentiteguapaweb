package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.CategoryRepo;


@Component
public class CategoryConverter implements Converter<String, Category> {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryConverter (CategoryRepo catRepo) {

        this.categoryRepo = catRepo;
    }

    @Override
    public Category convert(String arg) {
    	return categoryRepo.get(Integer.valueOf(arg));
    }
}

