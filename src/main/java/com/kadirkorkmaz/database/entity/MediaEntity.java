package com.kadirkorkmaz.database.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "media")
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type",nullable = false)
    private MediaTypeEnum type;

    @Column(length = 128, nullable = true)
    private String name;

    @Column(length = 512, nullable = true)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    //FIXME: make unique
    @Column(length = 128, nullable = false)
    private String url; //Long.toString(System.currentTimeMillis());

    public MediaEntity() {
    }

    public MediaEntity(MediaTypeEnum type, String name, String description, String url) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.url = url;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MediaTypeEnum getType() {
        return type;
    }

    public void setType(MediaTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return  "/uploaded-images/" + url;
    }
    
}
