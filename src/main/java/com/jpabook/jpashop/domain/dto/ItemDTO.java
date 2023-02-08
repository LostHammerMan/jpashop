package com.jpabook.jpashop.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

    String name;
    int price;
    int stockQuantity;
}
