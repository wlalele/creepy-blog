package fr.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blog.dao.AuthorRepository;
import fr.blog.entities.AuthorEntity;

@RestController
public class AuthorController {
	
	private final AuthorRepository repository;

	@Autowired
	public AuthorController(AuthorRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/author/list")
	public String listAction() {
		Iterable<AuthorEntity> list = this.repository.findAll();
		String view = "<h1>Author list:</h1> \n";
		for (AuthorEntity author : list) {
			view += "name: " + author.getName() + "\n";
		}
		return view;
	}
	
	@RequestMapping("/author/new")
	public String createAction(@RequestParam String name) {
		AuthorEntity author = new AuthorEntity();
		author.setName(name);
		this.repository.save(author);
		return "Author was succesfully created";
	}
	
	@RequestMapping("/author/view")
	public String viewAction(@RequestParam String name) {
		AuthorEntity author = this.repository.findByName(name).get(0);
		return "Author: \n"
				+ "Name: " + author.getName() + "\n";
	}
}