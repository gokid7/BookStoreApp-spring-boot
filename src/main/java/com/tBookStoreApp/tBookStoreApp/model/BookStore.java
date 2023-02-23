package com.tBookStoreApp.tBookStoreApp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookStore")
@Getter
@Setter
@NoArgsConstructor
public class BookStore {

    public BookStore(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bookStore_name")
    private String name;

    @OneToMany(mappedBy = "bookStore" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

}
