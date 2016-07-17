package com.kadirkorkmaz.service;

import com.kadirkorkmaz.database.entity.NavigationEntity;
import com.kadirkorkmaz.database.repository.NavigationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class NavigationService {

    @Autowired
    NavigationRepository navigationRepository;

    @Secured({"ADMIN", "WRITER"})
    public List<NavigationEntity> getAllNavigationContent() {
        return navigationRepository.findAll();
    }

    public List<NavigationEntity> getAllNavigationContentForUser() {
        return navigationRepository.findAllByAnonymousEnabled();
    }

    public List<NavigationEntity> getAllNavigationContentForAdmin() {
        return navigationRepository.findAllByAnonymousDisabled();
    }

    public NavigationEntity registerNavigationEntry(NavigationEntity navEnt) {
        return navigationRepository.save(navEnt);
    }

    public NavigationEntity findOrCreateNavigationEntry(String name, String link, boolean anonymousEnabled, int viewOrder) {
        NavigationEntity entry = navigationRepository.findOne(name, link, anonymousEnabled);

        if (entry == null) {
            entry = navigationRepository.save(new NavigationEntity(name, link, anonymousEnabled, viewOrder));
        }

        return entry;
    }

}
