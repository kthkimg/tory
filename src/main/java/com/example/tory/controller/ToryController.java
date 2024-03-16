package com.example.tory.controller;

import com.example.tory.domain.LoginForm;
import com.example.tory.domain.Member;
import com.example.tory.domain.Post;
import com.example.tory.service.ToryService;
import com.example.tory.store.ToryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tory")
public class ToryController {

    private final ToryRepository toryRepository;
    private final ToryService toryService;

    @GetMapping("/view")
    public String view(){
        return "tory/view";
//        return "templates/tory/home";
    }

    @GetMapping("/list")
    public String list(){
        return "tory/list";
    }

    @GetMapping("/member_add")
    public String memberAddForm(@ModelAttribute("member") Member member){
        return "tory/memberAdd";
    }

    @PostMapping("/member_add")
    public String memberSave(@ModelAttribute Member member){
        toryRepository.addMember(member);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        Member member = toryService.login(loginForm);
        log.info("login? {}", member);

        if(member == null){
            log.info("로그인 실패");
            return "redirect:/";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        log.info("로그인 성공");
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("로그아웃");
        return "redirect:/";
    }

    @GetMapping("/write")
    public String write(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model){
        model.addAttribute("post", new Post());
        if(member == null){
            log.info("logout 글쓰기");
            return "tory/write";
        }
        log.info("login 글쓰기");
        model.addAttribute("member", member);
        return "tory/loginWrite";
    }

    @PostMapping("/write")
    public String addPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                            @ModelAttribute("post") Post post, RedirectAttributes redirectAttributes){
        post.setIsMember("N");
        if(member != null){
            post.setAuthor(member.getId());
            post.setIsMember("Y");
            post.setMemberSeq(member.getMemberSeq());
        }
        toryRepository.addPost(post);
        return "redirect:/";
    }

    @GetMapping("/post/{postSeq}")
    public String getPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                          @ModelAttribute("post") Post post, Model model,
                          @PathVariable("postSeq") String postSeq){
        log.info("postSeq: {}", postSeq);
        return "tory/logoutView";
    }


}












