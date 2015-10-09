package fr.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blog.dao.CategoryRepository;
import fr.blog.entities.CategoryEntity;

@RestController
public class CategoryController {
	
	private final CategoryRepository repository;

	@Autowired
	public CategoryController(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/category/list")
	public String listAction() {
		Iterable<CategoryEntity> list = this.repository.findAll();
		String view = "<h1>Category list</h1> \n";
		for (CategoryEntity category : list) {
			view += "name: " + category.getName() + "\n";
		}
		return view;
	}
	
	@RequestMapping("/category/new")
	public String createAction(@RequestParam String name) {
		CategoryEntity category = new CategoryEntity();
		category.setName(name);
		this.repository.save(category);
		return "Category was succesfully created";
	}
	
	@RequestMapping("/category/view")
	public String viewAction(@RequestParam String name) {
		CategoryEntity category = this.repository.findByName(name).get(0);
		return "<h1>Category:</h1> \n"
				+ "Name: " + category.getName() + "\n";
	}
}