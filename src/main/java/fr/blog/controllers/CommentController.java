package fr.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blog.dao.CommentRepository;
import fr.blog.entities.CommentEntity;

@RestController
public class CommentController {
	
	private final CommentRepository repository;

	@Autowired
	public CommentController(CommentRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/comment/list")
	public String listAction() {
		Iterable<CommentEntity> list = this.repository.findAll();
		String view = "<h1>Comment list:</h1> \n";
		for (CommentEntity comment : list) {
			view += "comment: " + comment.getContent() + " - " + comment.getAuthor_id() + "\n";
		}
		return view;
	}
	
	@RequestMapping("/comment/new")
	public String createAction(@RequestParam Long article_id, Long author_id, String content) {
		CommentEntity comment = new CommentEntity();
		comment.setArticle_id(article_id);
		comment.setAuthor_id(author_id);
		comment.setContent(content);
		this.repository.save(comment);
		return "Comment was succesfully created";
	}
}