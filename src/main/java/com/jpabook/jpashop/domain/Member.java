package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    // @GeneratedValue : sequence 값 사용
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 내장 타입 사용시 사용(@Embeddable , @Embedded 하나만 있어도 되나, 내장 타입인 것을 보여주기 위해 둘 다 사용)
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Member 입장에서는 1:n 관계이므로 // order table의 Member의 거울(연관관계 주인 아님)
    private List<Order> orders = new ArrayList<>();


}
