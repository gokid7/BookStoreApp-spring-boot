package com.tBookStoreApp.tBookStoreApp;

import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.repository.BookRepository;
import com.tBookStoreApp.tBookStoreApp.repository.BookStoreRepository;
import com.tBookStoreApp.tBookStoreApp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TBookStoreAppApplication implements CommandLineRunner {
	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	private final BookStoreRepository bookStoreRepository;

	public TBookStoreAppApplication(BookRepository bookRepository,CategoryRepository categoryRepository,BookStoreRepository bookStoreRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
		this.bookStoreRepository = bookStoreRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TBookStoreAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category("Kategori 1");
		BookStore bs1 = new BookStore("BookStore 1");
		Book b1 = new Book("Kitap 1",c1,new BigDecimal(10),bs1);

		Category c2 = new Category("Kategori 2");
		BookStore bs2 = new BookStore("BookStore 2");
		Book b2 = new Book("Kitap 2",c2,new BigDecimal(20),bs2);

		Category c3 = new Category("Kategori 3");
		BookStore bs3 = new BookStore("BookStore 3");
		Book b3 = new Book("Kitap 3",c3,new BigDecimal(30),bs3);

		categoryRepository.saveAll(Arrays.asList(c1,c2,c3));
		bookStoreRepository.saveAll(Arrays.asList(bs1,bs2,bs3));
		List<Book> bookList = bookRepository.saveAll(Arrays.asList(b1,b2,b3));


		System.out.println(bookList);
	}
}
