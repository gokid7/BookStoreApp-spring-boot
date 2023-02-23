package com.tBookStoreApp.tBookStoreApp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String categoryName;
    private String bookStoreName;
}
