package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.domain.OrderSearch;
import com.jpabook.jpashop.service.ItemService;
import com.jpabook.jpashop.service.MemberService;
import com.jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    // 실무 : 주문 등은 Controller에 식별자만 넘김, 비즈니스 로직은 service에 작성

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){
        // @RequestParam : option에 의해 선택된 value 값 등 추출, name 으로 넘어온다.
        orderService.order(memberId, itemId, count); // 일단 하나만 주문 가능
        return "redirect:/orderList";

    }

    // OrderSearch 에서 검색에 필요한 정보들을 가지고 넘어옴
    @GetMapping("/orderList")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        log.info("orderList called.....");
        log.info("orderSearch = {}", orderSearch);
        log.info("orderSearch.getOrderStatus = {}", orderSearch.getOrderStatus());
        log.info("orderSearch.getMemberName() = {}", orderSearch.getMemberName());
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orderList")
    public String orderList2(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        log.info("orderList called2.....");
        log.info("orderSearch2 = {}", orderSearch);
        log.info("orderSearch.getOrderStatus2 = {}", orderSearch.getOrderStatus());
        log.info("orderSearch.getMemberName()2 = {}", orderSearch.getMemberName());
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orderStatus", OrderStatus.values());
        model.addAttribute("orders", orders);
        return "order/orderList";
    }


    // 취소
    @PostMapping("/orderList/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orderList";
    }



}
