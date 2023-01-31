package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
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

     /*stockQuantity 를 변경하고자 하는 경우 setter를 통해 직접 접근하는 것이 아니라
     별도의 메서드를 통해 접근해야 한다.*/
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 도메인 주도 설계
    // 엔티티 내부에서 해결할 수 있는 비즈니스 로직은 엔티티 내에 작성

    // == 비즈니스 로직 == //

    // 재고 증가 로직
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0 ){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
