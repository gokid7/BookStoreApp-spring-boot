package com.tBookStoreApp.tBookStoreApp.requests;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BookRequest {

    private Long id;
    private String name;
    private String categoryName;
    private String bookStoreName;
    private BigDecimal price;
}
