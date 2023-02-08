package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.dto.ItemDTO;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 더티체킹
    @Transactional
    public void updateItem(Long itemId, Book param){ // param : 파라미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); // itemId를 기반으로 DB상 해당 item 찾아옴
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

        itemRepository.save(findItem);
    }

    // 더티체킹 더 나은 방법
    @Transactional
    public void updateItem3(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);

        // 이 방법도 setter 대신 메서드를 만들어 처리하는 것이 좋음
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

    }

    // 더 나은 방법 2(dto 사용) - 속성이 많은 경우
    /*@Transactional
    public void updateItem4(Long itemId, ItemDTO itemDTO){
        Item findItem = itemRepository.findOne(itemId);
       // findItem.setName(itemDTO.getName());
       // findItem.setPrice(itemDTO.getPrice());

       // 메서드를 하나 만들어 한번에 값을 변경하는 것이 유지보수에 좋다
    }*/

    // 병합
    @Transactional
    public Item updateItem2(Long itemId, Book param){ // param : 파라미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); // itemId를 기반으로 DB상 해당 item 찾아옴
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

        return findItem;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
