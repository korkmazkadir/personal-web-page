package com.kadirkorkmaz.database.repository;

import com.kadirkorkmaz.database.entity.MediaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Kadir Korkmaz
 */
public interface MediaReository extends PagingAndSortingRepository<MediaEntity, Long> {

    public Page<MediaEntity> findAll(Pageable pegable);
}
