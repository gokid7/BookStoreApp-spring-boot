package com.tBookStoreApp.tBookStoreApp.repository;

import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore,Long> {
    BookStore findByName(String name);

}
