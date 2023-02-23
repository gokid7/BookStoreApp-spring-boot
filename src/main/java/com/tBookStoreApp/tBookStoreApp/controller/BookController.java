package com.tBookStoreApp.tBookStoreApp.controller;


import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.requests.BookRequest;
import com.tBookStoreApp.tBookStoreApp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{bookId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId){
        log.info("Kitap aranıyor {}",bookId);
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookRequest bookRequest){
        bookService.addBook(bookRequest);
        log.info("Kitap eklendi. {}",bookRequest.getName());
    }

    @PostMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> getAllCategories(){
        log.info("Books listeleniyor {}");
        List<BookDto> bookList = bookService.getAllBooks();
        return bookList;
    }

    @PostMapping(value = "/remove-book-from-bookStore" , produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeBookFromBookStore(@RequestBody BookRequest bookRequest){
        log.info("Book siliniyor...");
        boolean result = bookService.removeBookFromBookStore(bookRequest);
        return result ? "Book silindi" : "Hata alındı";
    }

    @PutMapping(value = "/change-book-category" , produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto changeBookCategory(@RequestBody BookRequest bookRequest){
        log.info("Book category değiştiriliyor...");
        BookDto bookDto = bookService.changeBookCategory(bookRequest);
        return bookDto;
    }

}
