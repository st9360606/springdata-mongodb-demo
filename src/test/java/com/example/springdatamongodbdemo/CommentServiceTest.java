package com.example.springdatamongodbdemo;

import com.example.springdatamongodbdemo.po.Comment;
import com.example.springdatamongodbdemo.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

//SpringBoot的Junit整合測試
@RunWith(SpringRunner.class)
//SpringBoot的測試環境初始化，參數：啟動類
@SpringBootTest(classes = SpringdataMongodbDemoApplication.class)
public class CommentServiceTest {
    //注入Service
    @Autowired
    private CommentService commentService;

    /**
     * 儲存一則評論
     */
    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setArticleid("100000");
        comment.setContent("測試新增的資料");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("凱撒大帝");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        commentService.saveComment(comment);
    }

    /**
     * 查詢所有數據
     */
    @Test
    public void testFindAll() {
        List<Comment> list = commentService.findCommentList();
        System.out.println(list);
    }

    /**
     * 測試根據id查詢
     */
    @Test
    public void testFindCommentById() {
        Comment comment = commentService.findCommentById("1");
        System.out.println(comment);
    }

    /**
     * 測試根據父id查詢子評論的分頁列表
     */
    @Test
    public void testFindCommentListPageByParentid() {
        Page<Comment> pageResponse = commentService.findCommentListByParentid("3", 1, 2);
        System.out.println("----總記錄數：" + pageResponse.getTotalElements());
        System.out.println("----目前頁資料：" + pageResponse.getContent());
    }

    /**
     * 讚數+1
     */
    @Test
    public void testUpdateCommentLikenum() {
        //對1號文檔的按讚數+1
        commentService.updateCommentLikenum("1");
    }
}
