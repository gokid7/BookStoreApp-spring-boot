package com.tBookStoreApp.tBookStoreApp.repository;

import com.tBookStoreApp.tBookStoreApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
