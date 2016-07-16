package com.kadirkorkmaz.service;

import com.kadirkorkmaz.database.entity.MediaEntity;
import com.kadirkorkmaz.database.repository.MediaReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kadir Korkmaz
 */
@Service
public class MediaService {
    
    @Autowired
    MediaReository mediaRepository;
    
    public Page<MediaEntity> getImages(int pageNumber,int pageSize){
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return mediaRepository.findAll(pageable);
    }

    
}
