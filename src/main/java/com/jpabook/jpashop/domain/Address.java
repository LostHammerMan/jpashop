package com.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// @Embeddable 지정한 클래스 : value 클래스
// value 클래스 : double 과 같이 하나의 값을 나타내는 클래스
// 여러 개의 값을 가지지만 개념적으로 하나의 값을 표현
// 다른 value 객체와 식별하기 위한 식별자를 가지지 않음
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
