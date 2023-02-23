package com.tBookStoreApp.tBookStoreApp.service;

import com.tBookStoreApp.tBookStoreApp.dto.BookDto;
import com.tBookStoreApp.tBookStoreApp.exception.ResourceNotFoundException;
import com.tBookStoreApp.tBookStoreApp.model.Book;
import com.tBookStoreApp.tBookStoreApp.model.BookStore;
import com.tBookStoreApp.tBookStoreApp.model.Category;
import com.tBookStoreApp.tBookStoreApp.repository.BookRepository;
import com.tBookStoreApp.tBookStoreApp.requests.BookRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final BookStoreService bookStoreService;

    public BookService(BookRepository bookRepository,ModelMapper modelMapper,CategoryService categoryService, BookStoreService bookStoreService){
        this.modelMapper=modelMapper;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.bookStoreService = bookStoreService;
    }

    public BookDto getBook(long bookId){
        BookDto bookDto = bookRepository.findById(bookId)
                .map(src -> modelMapper.map(src,BookDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı.Id: " + bookId));

        return bookDto;
    }

    public void addBook(BookRequest bookRequest){
        Book book = new Book();
        book = modelMapper.map(bookRequest,Book.class);

        if(bookRequest.getCategoryName().isEmpty()){
           throw new ResourceNotFoundException("Kategori adı boş olamaz.");
        }
        if(bookRequest.getBookStoreName().isEmpty()){
            throw new ResourceNotFoundException("BookStore adı boş olamaz.");
        }
        Category c = categoryService.findCategoryByName(bookRequest.getCategoryName());
        if(c == null){
            c = categoryService.addCategory(bookRequest.getCategoryName());
        }
        BookStore bs = bookStoreService.findBookStoreByName(bookRequest.getBookStoreName());
        if(bs == null){
            bs = bookStoreService.addBookStore(bookRequest.getBookStoreName());
        }
        book.setCategory(c);
        book.setBookStore(bs);
        bookRepository.save(book);
    }

    public List<BookDto> getAllBooks(){
        List<BookDto> bookDtoList = bookRepository.findAll()
                .stream().map(src -> modelMapper.map(src,BookDto.class)).collect(Collectors.toList());
        return bookDtoList;
    }

    public boolean removeBookFromBookStore(BookRequest bookRequest){
        if(bookRequest != null && bookRequest.getId() != null){
            bookRepository.deleteById(bookRequest.getId());
            return true;
        }
        else{
            return false;
        }
    }
    public BookDto changeBookCategory(@RequestBody BookRequest bookRequest){
        BookDto bookDto = new BookDto();
        if(bookRequest != null && bookRequest.getId() != null && bookRequest.getCategoryName() != null){
            Category category = categoryService.findCategoryByName(bookRequest.getCategoryName());
            if(category == null){
                throw new ResourceNotFoundException("Kategori bulunamadı.");
            }
            else{
                Optional<Book> book = bookRepository.findById(bookRequest.getId());
                if (book.isPresent()) {
                    book.get().setCategory(category);
                    Book newBook = bookRepository.save(book.get());
                    bookDto = modelMapper.map(newBook,BookDto.class);
                } else {
                    throw new ResourceNotFoundException("Böyle bir kitap yok");
                }

            }
        }
        return bookDto;
    }



}
