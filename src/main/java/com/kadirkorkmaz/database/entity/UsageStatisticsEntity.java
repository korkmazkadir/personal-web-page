package com.kadirkorkmaz.database.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usage_statistics")
public class UsageStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String pageUrl;

    @Column(columnDefinition = "default null")
    private Long articleId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    public UsageStatisticsEntity() {
    }

    public UsageStatisticsEntity(String userName, String sessionId, String pageUrl) {
        this.userName = userName;
        this.sessionId = sessionId;
        this.pageUrl = pageUrl;
    }

    public UsageStatisticsEntity(String userName, String sessionId, String pageUrl, Long articleId) {
        this.userName = userName;
        this.sessionId = sessionId;
        this.pageUrl = pageUrl;
        this.articleId = articleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getDate() {
        return date;
    }


}
