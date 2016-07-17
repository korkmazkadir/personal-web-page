package com.kadirkorkmaz.database.repository;

import com.kadirkorkmaz.database.entity.NavigationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NavigationRepository extends CrudRepository<NavigationEntity, Long> {

    List<NavigationEntity> findAll();

    @Query("select n from NavigationEntity n where n.anonymousEnabled = true")
    List<NavigationEntity> findAllByAnonymousEnabled();

    @Query("select n from NavigationEntity n where n.anonymousEnabled = false")
    List<NavigationEntity> findAllByAnonymousDisabled();

    @Query("select n from NavigationEntity n where n.name = ?1 and n.link = ?2 and n.anonymousEnabled = ?3")
    NavigationEntity findOne(String name, String link, boolean anonymousEnabled);

}
