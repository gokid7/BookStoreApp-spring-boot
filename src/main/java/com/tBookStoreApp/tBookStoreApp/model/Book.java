package com.tBookStoreApp.tBookStoreApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookStore_id",nullable = false)
    private BookStore bookStore;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    public Book(String name, Category category, BigDecimal price,BookStore bookStore) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.bookStore = bookStore;
    }
}
