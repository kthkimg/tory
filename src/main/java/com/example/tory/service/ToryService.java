package com.example.tory.service;

import com.example.tory.domain.*;
import com.example.tory.store.ToryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToryService {
    private final ToryRepository toryRepository;

    public Member login(LoginForm loginForm) {
        return toryRepository.findById(loginForm);
    }

    public PagingResponse<Post> findAllPost(SearchDto params){
        int count = toryRepository.getPostCount(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<Post> list = toryRepository.findAllPost(params);
        return new PagingResponse<>(list, pagination);
    }
}


















