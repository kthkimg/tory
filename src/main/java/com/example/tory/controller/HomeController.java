package com.example.tory.controller;

import com.example.tory.domain.LoginForm;
import com.example.tory.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    private String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model, @ModelAttribute("loginForm")LoginForm form) {
        if(member != null){
            model.addAttribute("member", member);
        }
        return "tory/list";
    }



}






























