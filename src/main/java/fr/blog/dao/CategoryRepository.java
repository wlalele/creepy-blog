package fr.blog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.blog.entities.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByName(String name);
}