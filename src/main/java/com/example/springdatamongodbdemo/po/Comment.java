package com.example.springdatamongodbdemo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文章評論實體類
 */
//把一個java類別宣告為mongodb的文檔，可以透過collection參數指定這個類別對應的文檔。
//@Document(collection="mongodb 對應 collection 名")
// 若未加 @Document ，該 bean save 到 mongo 的 comment collection
// 若新增 @Document ，則 save 到 comment collection
@Document(collection = "comment")//可以省略，如果省略，則預設使用類別名稱小寫映射集合
//複合索引
 @CompoundIndex( def = "{'userid': 1, 'nickname': -1}")  //1:升序  -1:降序
public class Comment implements Serializable {
    //主鍵標識，該屬性的值會自動對應mongodb的主鍵字段"_id"，如果該屬性名就叫“id”,則該註解可以省略，否則必須寫
    @Id
    private String id;//主鍵
    //該屬性對應mongodb的欄位的名字，如果一致，則無需該註解
    @Field("content")
    private String content;//吐槽內容
    private Date publishtime;//發布日期
    //新增了一個單一欄位的索引
    @Indexed
    private String userid;//發佈人ID
    private String nickname;//暱稱
    private LocalDateTime createdatetime;//評論的日期時間
    private Integer likenum;//按讚數
    private Integer replynum;//回覆數
    private String state;//狀態
    private String parentid;//上級ID
    private String articleid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishtime=" + publishtime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createdatetime=" + createdatetime +
                ", likenum=" + likenum +
                ", replynum=" + replynum +
                ", state='" + state + '\'' +
                ", parentid='" + parentid + '\'' +
                ", articleid='" + articleid + '\'' +
                '}';
    }
}