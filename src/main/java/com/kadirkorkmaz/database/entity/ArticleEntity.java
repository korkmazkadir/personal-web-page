package com.kadirkorkmaz.database.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(length = 512, unique = true)
    private String name;
    
    @Column(length = 64000)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity writer;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    
    @Column(nullable = false)
    private boolean isVisible = true;

    @Column(columnDefinition="default 1000")
    private int viewOrder = 1000;
    
    @Column(columnDefinition="default false")
    private boolean isDeleted = false;

    public ArticleEntity() {
    }

    public ArticleEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public ArticleEntity(long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getWriter() {
        return writer;
    }

    public void setWriter(UserEntity writer) {
        this.writer = writer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(int viewOrder) {
        this.viewOrder = viewOrder;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPathString(){
        return name.replaceAll("\\s+","-");
    }
    
    @Override
    public String toString() {
        return name;
    }


}
