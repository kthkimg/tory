package com.example.tory.store;

import com.example.tory.domain.LoginForm;
import com.example.tory.domain.Member;
import com.example.tory.domain.Post;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ToryRepository {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int addMember(Member member) {
        return sqlSessionTemplate.insert("toryMapper.addMember", member);
    }

    public Member findById(LoginForm loginForm){
        return sqlSessionTemplate.selectOne("toryMapper.findById", loginForm);
    }

    public int addPost(Post post){
        return sqlSessionTemplate.insert("toryMapper.addPost", post);
    }
}















