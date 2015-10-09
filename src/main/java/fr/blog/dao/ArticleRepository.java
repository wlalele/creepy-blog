package fr.blog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.blog.entities.ArticleEntity;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    List<ArticleEntity> findByTitle(String title);
}