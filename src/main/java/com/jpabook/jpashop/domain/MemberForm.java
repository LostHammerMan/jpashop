package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
