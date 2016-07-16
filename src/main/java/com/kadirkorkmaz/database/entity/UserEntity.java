package com.kadirkorkmaz.database.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 512, nullable = false, unique = true)
    private String userName;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String surname;

    @Column(length = 64, nullable = false)
    private String password;

    @OneToMany(mappedBy = "writer", fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
    private Set<ArticleEntity> articles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Set<UserRole> roles = new LinkedHashSet<>();

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String userName, String password) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.password = password;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addToArticles(ArticleEntity article) {
        article.setWriter(this);
        this.articles.add(article);
    }

    public void addToRoles(UserRole role) {
        role.setUser(this);
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", name=" + name + ", surname=" + surname + ", password=" + password + ", articles=" + articles + ", roles=" + roles + '}';
    }

}
