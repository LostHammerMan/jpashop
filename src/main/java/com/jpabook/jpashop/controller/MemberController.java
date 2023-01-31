package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.MemberForm;
import com.jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";

    }
}
