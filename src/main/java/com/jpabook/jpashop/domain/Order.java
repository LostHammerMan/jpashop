package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // Order와 Member 는 n:1 관계, 멤버 한명이 다수의 주문 할 수 있기 때문
    @ManyToOne
    @JoinColumn(name = "member_id") // Member table의 member_id가 FK 가 되도록 매핑
    private Member member;

    private List<OrderItem> orderItems = new ArrayList<>();

}
