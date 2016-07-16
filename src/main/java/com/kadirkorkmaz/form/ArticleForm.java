package com.kadirkorkmaz.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ArticleForm {
    
    private long id;
    
    @NotNull
    @Size(min = 2, max = 256)
    private String name;
    
    @NotNull
    @Size(min = 2)
    private String content;
    

    public ArticleForm() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
