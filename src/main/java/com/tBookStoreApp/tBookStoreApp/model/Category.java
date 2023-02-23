package com.tBookStoreApp.tBookStoreApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="category_name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
