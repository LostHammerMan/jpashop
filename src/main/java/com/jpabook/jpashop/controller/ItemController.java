package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.BookForm;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    private String createForm(Model model){
        model.addAttribute("bookForm", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(BookForm bookForm){

        // 팁 : 메서드를 사용해 setter 를 제거하는 것이 좋은 설계
        Book book = new Book();

        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/lists")
    public String itemList(Model model){

        List<Item> itemList = itemService.findItems();
        model.addAttribute("itemList", itemList);
        return "items/itemLists";

    }

    @GetMapping("/lists/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book)itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());

        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("updateForm", form);
        return "items/updateItemForm";
    }
//items/lists/1/items
    @PostMapping("/lists/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId ,@ModelAttribute("form") BookForm form){

        // BookForm -> 웹 계층에서만 사용하도록 설계

        /*Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);*/

        // 더 나은 설계(어설프게 엔티티 사용하지 않고, 필요한 데이터만 받은 것), 받을 데이터가 많은 경우 dto 만들어 사용
        itemService.updateItem3(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items/lists";
    }
}
