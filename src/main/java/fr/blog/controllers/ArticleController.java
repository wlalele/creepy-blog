package fr.blog.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blog.dao.ArticleRepository;
import fr.blog.dao.AuthorRepository;
import fr.blog.dao.CategoryRepository;
import fr.blog.dao.CommentRepository;
import fr.blog.entities.ArticleEntity;
import fr.blog.entities.CommentEntity;

@RestController
public class ArticleController {
	
	private final ArticleRepository repository;
	private final AuthorRepository author_repository;
	private final CategoryRepository category_repository;
	private final CommentRepository comment_repository;

	@Autowired
	public ArticleController(ArticleRepository repository, AuthorRepository author_repository, CategoryRepository category_repository, CommentRepository comment_repository) {
		this.repository = repository;
		this.author_repository = author_repository;
		this.category_repository = category_repository;
		this.comment_repository = comment_repository;
	}
	
	@RequestMapping("/article/list")
	public String listAction() {
		Iterable<ArticleEntity> list_articles = this.repository.findAll();
		String view = "<h1>Author list:</h1> \n";
		for (ArticleEntity article : list_articles) {
			Iterable<CommentEntity> list_comments = this.comment_repository.findByArticleId(article.getId());
			view += "<div>";
			view += "<ul>";
			view += "<li>title: " + article.getTitle() + "</li>";
			view += "<li>category: " + this.category_repository.findOne(article.getCategory_id()).getName() + "</li>";
			view += "<li>author: " + this.author_repository.findOne(article.getAuthorId()).getName() + "</li>";
			view += "<li>" + ((Collection<?>)list_comments).size() + " comment(s)</li>";
			view += "</ul>";
			view += "</div>";
		}
		
		return view;
	}
	
	@RequestMapping("/article/new")
	public String createAction(@RequestParam String title, Long category_id, String content, Long author_id) {
		ArticleEntity article = new ArticleEntity();
		article.setTitle(title);
		article.setCategory_id(category_id);
		article.setContent(content);
		article.setAuthorId(author_id);
		article.setPublished_at(null);
		this.repository.save(article);
		return "Article <"+ title +"> was succesfully created";
	}
	
	@RequestMapping("/article/view")
	public String viewAction(@RequestParam Long id) {
		ArticleEntity article = this.repository.findOne(id);
		String view = "";
		view += "<div>";
		view += "<h1>Article:</h1>";
		view += "<ul>";
		view += "<li>title: " + article.getTitle() + "</li>";
		view += "<li>" + article.getContent() + "</li>";
		view += "<li>category: " + this.category_repository.findOne(article.getCategory_id()).getName() + "</li>";
		view += "<li>author: " + this.author_repository.findOne(article.getAuthorId()).getName() + "</li>";
		view += "</ul>";
		view += "<h2>Comments:</h2>";
		view += "<ul>";
		Iterable<CommentEntity> list_comments = this.comment_repository.findByArticleId(article.getId());
		for (CommentEntity comment : list_comments) {
			view += "<li>" + this.author_repository.findOne(comment.getAuthor_id()).getName() + " - " + comment.getContent() + "</li>";
		}
		view += "</ul>";
		view += "</div>";
		return view;
	}
}