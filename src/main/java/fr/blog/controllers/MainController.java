package fr.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.blog.dao.ArticleRepository;
import fr.blog.dao.AuthorRepository;
import fr.blog.dao.CategoryRepository;
import fr.blog.dao.CommentRepository;
import fr.blog.entities.ArticleEntity;
import fr.blog.entities.AuthorEntity;
import fr.blog.entities.CategoryEntity;
import fr.blog.entities.CommentEntity;

@RestController
public class MainController {
	
	private final ArticleRepository article_repository;
	private final AuthorRepository author_repository;
	private final CategoryRepository category_repository;
	private final CommentRepository comment_repository;
	
	private boolean loaded = false;

	@Autowired
	public MainController(ArticleRepository article_repository, AuthorRepository author_repository, CategoryRepository category_repository, CommentRepository comment_repository) {
		this.article_repository = article_repository;
		this.author_repository = author_repository;
		this.category_repository = category_repository;
		this.comment_repository = comment_repository;
	}
	
	@RequestMapping("/init")
	public String initAction() {
		
		if (this.loaded) {
			return "DB already loaded";
		}
		
		AuthorEntity author = new AuthorEntity();
		author.setName("Amine");
		this.author_repository.save(author);
		
		AuthorEntity author2 = new AuthorEntity();
		author2.setName("Mathieu");
		this.author_repository.save(author2);
		
		//
		
		CategoryEntity category = new CategoryEntity();
		category.setName("Informatique");
		this.category_repository.save(category);
		
		CategoryEntity category2 = new CategoryEntity();
		category2.setName("Actualité");
		this.category_repository.save(category2);
		
		//
		
		ArticleEntity article = new ArticleEntity();
		article.setTitle("Nouveau format de fichier image");
		article.setCategory_id((long) 1);
		article.setAuthorId((long) 1);
		article.setContent("Voir FLIFF");
		this.article_repository.save(article);
		
		//
		
		CommentEntity comment = new CommentEntity();
		comment.setArticle_id((long) 1);
		comment.setAuthor_id((long) 2);
		comment.setContent("Trop bien !!!!");
		this.comment_repository.save(comment);
		
		this.loaded = true;
		
		return "Initialisation OK.";
	}
}