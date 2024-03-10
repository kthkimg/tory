package com.example.tory.store;

import com.example.tory.domain.LoginForm;
import com.example.tory.domain.Member;
import com.example.tory.domain.Post;
import com.example.tory.domain.SearchDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToryRepository {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Post> findAllPost(SearchDto params){
        return sqlSessionTemplate.selectList("toryMapper.findAllPost", params);
    }

    public int addMember(Member member) {
        return sqlSessionTemplate.insert("toryMapper.addMember", member);
    }

    public Member findById(LoginForm loginForm){
        return sqlSessionTemplate.selectOne("toryMapper.findById", loginForm);
    }

    public int addPost(Post post){
        return sqlSessionTemplate.insert("toryMapper.addPost", post);
    }

    public int getPostCount(SearchDto params){
        return sqlSessionTemplate.selectOne("toryMapper.getPostCount", params);
    }
}















