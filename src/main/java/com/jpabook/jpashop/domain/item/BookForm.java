package com.jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    // 상품 공통 속성
    private Long id; // 상품수정시 필요
    private String name;
    private int price;
    private int stockQuantity;

    // 책만의 속성
    private String author;
    private String isbn;
}
