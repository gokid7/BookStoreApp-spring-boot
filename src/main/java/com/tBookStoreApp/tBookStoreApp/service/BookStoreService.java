package com.tBookStoreApp.tBookStoreApp.service;

import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.dto.BookStoreDto;
import com.tBookStoreApp.tBookStoreApp.exception.ResourceNotFoundException;
import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.repository.BookStoreRepository;
import com.tBookStoreApp.tBookStoreApp.requests.BookStoreRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;
    private final ModelMapper modelMapper;

    public BookStoreService(BookStoreRepository bookStoreRepository, ModelMapper modelMapper) {
        this.bookStoreRepository = bookStoreRepository;
        this.modelMapper = modelMapper;
    }
    public void addBookStore(BookStoreRequest bookStoreRequest){
        BookStore bookStore = new BookStore();
        bookStore = modelMapper.map(bookStoreRequest, BookStore.class);
        bookStoreRepository.save(bookStore);
    }

    public BookStore findBookStoreByName(String bookStoreName){
        BookStore category = new BookStore();
        return bookStoreRepository.findByName(bookStoreName);
    }

    public BookStore addBookStore(String bookStoreName){
        BookStore bookStore = new BookStore();
        bookStore.setName(bookStoreName);
        return bookStoreRepository.save(bookStore);
    }

    public List<BookStoreDto> getAllBookStores(){
        List<BookStoreDto> bookStoreDtoList = bookStoreRepository.findAll()
                .stream().map(src -> modelMapper.map(src,BookStoreDto.class)).collect(Collectors.toList());
        return bookStoreDtoList;
    }
    public List<BookDto> getBooksByBookStoresId(BookStoreRequest bookStoreRequest ){
        if(bookStoreRequest.getId() == null){
            throw new ResourceNotFoundException("BookStore id boş olamaz.");
        }
        Optional<BookStore> bookStore = bookStoreRepository.findById(bookStoreRequest.getId());
        List<BookDto> bookDtoList = new ArrayList<>();
        if(bookStore.isPresent()){
            //TODO:kategori boş olunca exception
            List<Book> bookList = bookStore.stream().flatMap(src -> src.getBookList().stream()).collect(Collectors.toList());
            bookDtoList = bookList.stream().map(src -> modelMapper.map(src,BookDto.class)).collect(Collectors.toList());
        }
        return bookDtoList;
    }

}
