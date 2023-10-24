package com.example.springdatamongodbdemo.service;

import com.example.springdatamongodbdemo.dao.CommentRepository;
import com.example.springdatamongodbdemo.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    //注入dao
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 儲存一則評論
     *
     * @param comment
     */
    public void saveComment(Comment comment) {
        //如果需要自訂主鍵，可以在這裡指定主鍵；如果不指定主鍵，MongoDB會自動產生主鍵
        commentRepository.save(comment);
    }

    /**
     * 更新評論
     *
     * @param comment
     */
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 根據id刪除評論
     *
     * @param id
     */
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    /**
     * 查詢所有評論
     *
     * @return
     */
    public List<Comment> findCommentList() {
        return commentRepository.findAll();
    }

    /**
     * 根據id查詢評論
     *
     * @param id
     * @return
     */
    public Comment findCommentById(String id) {
        return commentRepository.findById(id).get();
    }

    /**
     * 根據父id查詢分頁列表
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findCommentListByParentid(String parentid, int page, int size) {
        return commentRepository.findByParentid(parentid, PageRequest.of(page - 1, size));
    }

    /**
     *按讚數+1
     *@paramid
     */
    public void updateCommentLikenum(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("likenum");
        //參數1：查詢對象
        //參數2：更新對象
        //參數3：集合的名字或實體類別的型別Comment.class
        mongoTemplate.updateFirst(query, update, Comment.class);
    }
}