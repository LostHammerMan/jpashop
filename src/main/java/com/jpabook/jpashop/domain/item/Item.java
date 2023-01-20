package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 구현체를 가지고 작업할 것이므로 추상 클래스로 생성
// 추상 클래스 : 하나 이상의 추상 메서드를 포함하는 클래스
// 동작이 정의되어 있지 않은 추상 메서드를 포함하고 있으므로, 인스턴스 생성 불가
// 상속을 통해 자식 클래스를 만들고, 자식 클래스에서 모든 추상 메서드를 오버라이딩 해야 인스턴스 생성 가능
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
