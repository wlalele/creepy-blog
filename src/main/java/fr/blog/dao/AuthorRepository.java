package fr.blog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.blog.entities.AuthorEntity;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByName(String name);
}