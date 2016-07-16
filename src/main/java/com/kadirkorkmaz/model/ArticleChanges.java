package com.kadirkorkmaz.model;

public class ArticleChanges {

    private long articleId;
    private int articleIndex;
    private boolean isVisible;
    private boolean deleted;

    public ArticleChanges() {
    }

    public ArticleChanges(long articleId, int articleIndex) {
        this.articleId = articleId;
        this.articleIndex = articleIndex;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
