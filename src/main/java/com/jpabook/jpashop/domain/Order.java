package com.jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // Order와 Member 는 n:1 관계, 멤버 한명이 다수의 주문 할 수 있기 때문
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // Member table의 member_id가 FK 가 되도록 매핑
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, Cancel]

    // == 연관관계 편의 메서드 == -> 연관관계를 핵심적으로 컨트롤하는 클래스에 작성, 양방향시 사용
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // == 생성 메서드 == //
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
        // == 비즈니스 로직 == //

        // 주문 취소
        public void cancel(){
            if (delivery.getStatus() == DeliveryStatus.COMP){
                throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
            }

            this.setStatus(OrderStatus.CANCEL);

            for (OrderItem orderItem : orderItems){
                orderItem.cancel();
            }
        }

        // == 조회 로직 == //

    // 전체 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;

        for (OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }



}
