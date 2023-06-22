package com.tBookStoreApp.tBookStoreApp.controller;

import com.tBookStoreApp.tBookStoreApp.aspect.ExecutionTime;
import com.tBookStoreApp.tBookStoreApp.constant.Mappings;
import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.dto.BookStoreDto;
import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.repository.BookStoreRepository;
import com.tBookStoreApp.tBookStoreApp.requests.BookRequest;
import com.tBookStoreApp.tBookStoreApp.requests.BookStoreRequest;
import com.tBookStoreApp.tBookStoreApp.requests.CategoryRequest;
import com.tBookStoreApp.tBookStoreApp.service.BookStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping(Mappings.BOOKSTORE)
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @ExecutionTime
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCategory(@RequestBody BookStoreRequest bookStoreRequest){
        log.info("BookStore ekleniyor {}",bookStoreRequest.getName());
        bookStoreService.addBookStore(bookStoreRequest);
    }

    @ExecutionTime
    @PostMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookStoreDto>> getAllBookStores(){
        log.info("Bookstores listeleniyor...");
        List<BookStoreDto> bookStoreList = bookStoreService.getAllBookStores();
        return ResponseEntity.ok(bookStoreList);
    }

    @ExecutionTime
    @PostMapping(value = "/books-by-bookStores-id" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> getBooksByBookStoresId(@RequestBody BookStoreRequest bookStoreRequest){
        log.info("BookStores idye göre listeleniyor...");
        List<BookDto> bookDtoList = bookStoreService.getBooksByBookStoresId(bookStoreRequest);
        return ResponseEntity.ok(bookDtoList);
    }

}
