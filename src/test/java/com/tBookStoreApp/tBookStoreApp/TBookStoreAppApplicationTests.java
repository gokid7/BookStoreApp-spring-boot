package com.tBookStoreApp.tBookStoreApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.repository.BookRepository;
import com.tBookStoreApp.tBookStoreApp.requests.BookRequest;
import com.tBookStoreApp.tBookStoreApp.service.CategoryService;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TBookStoreAppApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getBook_whenGetBook_success() throws Exception {
		mvc.perform(get("/books/4")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("name", is("Test Kitap")));
	}

	@Test
	void getBook_whenGetBook_fail() throws Exception {
		mvc.perform(get("/books/6")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("statusCode", is(404)));
	}

	@Test
	void addBook_whenAddBook_success() throws Exception {
		BookRequest bookRequest = createTestBookRequest();
		mvc.perform(post("/books/add")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(bookRequest)));

		List<Book> found = bookRepository.findAll();
		assertThat(found).extracting(Book::getName).contains("BookRequest");
	}

	@BeforeEach
	public void addBook(){
		bookRepository.save(createTestBook4());
	}

	private Book createTestBook4(){
		Category testCategory= new Category("Kategori 1");
		testCategory.setId(Long.valueOf(1));
		BookStore testBookStore = new BookStore("BookStore 1");
		testBookStore.setId(Long.valueOf(1));
		Book testBook = new Book("Test Kitap",testCategory,new BigDecimal(10),testBookStore);
		testBook.setId(Long.valueOf(4));
		return testBook;
	}

	private BookRequest createTestBookRequest(){
		BookRequest bookRequest = new BookRequest();
		//bookRequest.setId(Long.valueOf(6));
		bookRequest.setName("BookRequest");
		bookRequest.setPrice(new BigDecimal("15"));
		bookRequest.setCategoryName("Test Kategori");
		bookRequest.setBookStoreName("Test BookStore");
		return bookRequest;
	}

	@After
	public void resetDb() {
		bookRepository.deleteAll();
	}

}
