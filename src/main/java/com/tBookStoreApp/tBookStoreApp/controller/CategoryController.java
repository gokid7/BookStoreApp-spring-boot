package com.tBookStoreApp.tBookStoreApp.controller;

import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.dto.CategoryDto;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.requests.BookRequest;
import com.tBookStoreApp.tBookStoreApp.requests.CategoryRequest;
import com.tBookStoreApp.tBookStoreApp.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        log.info("Kategori ekleniyor {}",categoryRequest.getName());
        categoryService.addCategory(categoryRequest);
    }

    @PostMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategories(){
        log.info("Kategoriler listeleniyor...");
        List<CategoryDto> categoryArrayList = categoryService.getAllCategories();
        return categoryArrayList;
    }

    @PostMapping(value = "/books-by-category-id" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> getBooksByCategoryId(@RequestBody CategoryRequest categoryRequest){
        log.info("Kategoriler book id ye göre listeleniyor...");
        List<BookDto> bookDtoList = categoryService.getBooksByCategoryId(categoryRequest);
        return bookDtoList;
    }

}
