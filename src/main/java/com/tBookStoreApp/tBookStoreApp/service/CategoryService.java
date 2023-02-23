package com.tBookStoreApp.tBookStoreApp.service;

import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.dto.CategoryDto;
import com.tBookStoreApp.tBookStoreApp.exception.ResourceNotFoundException;
import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.repository.CategoryRepository;
import com.tBookStoreApp.tBookStoreApp.requests.CategoryRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository,ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public void addCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category = modelMapper.map(categoryRequest,Category.class);
        categoryRepository.save(category);
    }

    public Category findCategoryByName(String categoryName){
        Category category = new Category();
        return categoryRepository.findByName(categoryName);
    }

    public Category addCategory(String categoryName){
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    public List<CategoryDto> getAllCategories(){
        List<CategoryDto> categoryDtoList = categoryRepository.findAll()
                .stream().map(src -> modelMapper.map(src,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }

    public List<BookDto> getBooksByCategoryId(CategoryRequest categoryRequest){
        if(categoryRequest.getId() == null){
            throw new ResourceNotFoundException("Category id boş olamaz.");
        }
        Optional<Category> category = categoryRepository.findById(categoryRequest.getId());
        List<BookDto> bookDtoList = new ArrayList<>();
        if(category.isPresent()){
            //TODO:kategori boş olunca exception
            List<Book> bookList = category.stream().flatMap(src -> src.getBookList().stream()).collect(Collectors.toList());
            bookDtoList = bookList.stream().map(src -> modelMapper.map(src,BookDto.class)).collect(Collectors.toList());
        }

        return bookDtoList;
    }

}
