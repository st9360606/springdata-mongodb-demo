package com.example.springdatamongodbdemo.dao;

import com.example.springdatamongodbdemo.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {

    //根據父id，查詢子評論的分頁列表
    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
