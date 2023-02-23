package com.tBookStoreApp.tBookStoreApp.repository;

import com.tBookStoreApp.tBookStoreApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
