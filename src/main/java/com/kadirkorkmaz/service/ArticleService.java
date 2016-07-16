package com.kadirkorkmaz.service;

import com.kadirkorkmaz.database.entity.ArticleEntity;
import com.kadirkorkmaz.database.repository.ArticleRepository;
import com.kadirkorkmaz.model.ArticleChanges;
import java.util.List;
import javax.transaction.Transactional;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private PegDownProcessor pegDownProcessor;

    public List<ArticleEntity> getAllVisibleArticles() {
        List<ArticleEntity> allVisibleArticles = articleRepository.findAllVisibleArticles();
        return allVisibleArticles;
    }

    public List<ArticleEntity> getAllActiveArticles() {
        List<ArticleEntity> allVisibleArticles = articleRepository.findAllActiveArticles();
        return allVisibleArticles;
    }

    public ArticleEntity getArticle(long id) {
        return articleRepository.findOne(id);
    }

    public ArticleEntity saveArticle(ArticleEntity article) {
        return articleRepository.save(article);
    }

    public ArticleEntity updateArticle(ArticleEntity article) {
        ArticleEntity articleFromDb = articleRepository.findOne(article.getId());
        articleFromDb.setName(article.getName());
        articleFromDb.setContent(article.getContent());
        return articleRepository.save(articleFromDb);
    }

    public String converMarkdowntoHtml(String markdownString) {
        return pegDownProcessor.markdownToHtml(markdownString);
    }

    public ArticleEntity getConvertedArticle(long id) {
        ArticleEntity article = articleRepository.findOne(id);
        article.setContent(pegDownProcessor.markdownToHtml(article.getContent()));
        return article;
    }

    @Transactional
    public void updateOrderAndVisibility(ArticleChanges[] changes) {
        for (ArticleChanges c : changes) {
            articleRepository.updateOrderAndVisibility(c.getArticleId(), c.getArticleIndex(), c.isVisible(), c.isDeleted());
        }
    }

}
