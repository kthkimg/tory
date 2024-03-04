package com.example.tory.service;

import com.example.tory.domain.LoginForm;
import com.example.tory.domain.Member;
import com.example.tory.store.ToryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToryService {
    private final ToryRepository toryRepository;

    public Member login(LoginForm loginForm) {
        return toryRepository.findById(loginForm);
    }
}
