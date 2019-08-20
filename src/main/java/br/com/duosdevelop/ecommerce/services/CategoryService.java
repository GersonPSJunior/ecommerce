package br.com.duosdevelop.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duosdevelop.ecommerce.domain.Category;
import br.com.duosdevelop.ecommerce.repositories.CategoryRepository;
import br.com.duosdevelop.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category find(Long id) {
		Optional<Category> category = repository.findById(id);
		
		return category.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id +
				", Tipo:"+ Category.class.getName()));
	}
}