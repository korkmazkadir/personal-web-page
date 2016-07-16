/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "navigation")
public class NavigationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String link;

    @Column(columnDefinition="default false")
    private boolean anonymousEnabled;

    private int viewOrder;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    public NavigationEntity() {
    }

    public NavigationEntity(String name, String link, boolean anonymousEnabled, int viewOrder) {
        this.name = name;
        this.link = link;
        this.anonymousEnabled = anonymousEnabled;
        this.viewOrder = viewOrder;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isAnonymousEnabled() {
        return anonymousEnabled;
    }

    public void setAnonymousEnabled(boolean anonymousEnabled) {
        this.anonymousEnabled = anonymousEnabled;
    }

    public int getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(int viewOrder) {
        this.viewOrder = viewOrder;
    }


    public Date getCreationDate() {
        return creationDate;
    }

}
