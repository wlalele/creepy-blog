package fr.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "article_id", nullable = false)
	private Long articleId;
	
	@Column(name = "author_id", nullable = false)
	private Long author_id;
	
	@Column(name = "content", nullable = false)
	@Size(max = 255)
	private String content = "";
	
	public CommentEntity() {
		super();
	}

	public Long getArticle_id() {
		return articleId;
	}

	public void setArticle_id(Long article_id) {
		this.articleId = article_id;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
