package com.jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    /*// 로그 -> 어노테이션으로 대체 가능
    Logger log = LoggerFactory.getLogger(getClass());*/

    @RequestMapping("/")
    public String home(Model model){
        log.info("home Controller...");
        return "hello";
    }
}
