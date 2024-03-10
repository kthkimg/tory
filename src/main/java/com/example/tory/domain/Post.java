package com.example.tory.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private int postSeq;
    private String title;
    private String author;
    private String content;
    private String idDelete;
    private String isMember;
    private String password;
    private int memberSeq;
    private String createAt;
    private String updateAt;
}


















