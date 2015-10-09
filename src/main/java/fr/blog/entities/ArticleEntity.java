package fr.blog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class ArticleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "title", nullable = false)
	@Size(max = 64)
	private String title = "";
	
	@Column(name = "content", nullable = false)
	@Size(max = 255)
	private String content = "";
	
	@Column(name = "author_id", nullable = false)
	private Long author_id;
	
	@Column(name = "category_id", nullable = false)
	private Long category_id;
	
	@Column(name = "published_at", nullable = true)
	private Date published_at = null;
	
	public ArticleEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getAuthorId() {
		return author_id;
	}
	public void setAuthorId(Long author_id) {
		this.author_id = author_id;
	}
	
	public Date getPublished_at() {
		return published_at;
	}
	public void setPublished_at(Date published_at) {
		this.published_at = published_at;
	}
}
