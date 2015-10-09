package fr.blog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.blog.entities.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findByArticleId(Long article_id);
}