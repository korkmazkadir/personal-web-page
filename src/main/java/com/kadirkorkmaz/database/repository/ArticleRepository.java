package com.kadirkorkmaz.database.repository;

import com.kadirkorkmaz.database.entity.ArticleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {

    @Query("select a from ArticleEntity a where a.isDeleted = false")
    List<ArticleEntity> findAllActiveArticles();

    @Query("select a from ArticleEntity a where a.isDeleted = false and a.isVisible = true")
    List<ArticleEntity> findAllVisibleArticles();

    @Modifying
    @Query("update ArticleEntity a set a.viewOrder = ?2, a.isVisible =?3, a.isDeleted =?4 where a.id = ?1")
    int updateOrderAndVisibility(Long id, Integer order, Boolean isVisible, Boolean isDeleted);

}
