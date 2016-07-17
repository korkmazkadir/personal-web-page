package com.kadirkorkmaz.configuration;

import com.kadirkorkmaz.database.entity.NavigationEntity;
import com.kadirkorkmaz.service.NavigationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author kadir
 */
@Configuration
public class DefaultNavigationBarConfiguration {

    private static final Logger logger = Logger.getLogger(DefaultNavigationBarConfiguration.class);

    @Autowired
    NavigationService navigationService;
    
    @Bean
    public boolean registerDefaultNavigationElements() {

        logger.info("Registering default navigation bar entities.");

        boolean anonymousEnabled = false;

        NavigationEntity mainPage = new NavigationEntity("Ana Sayfa", "/", true, 1);
        NavigationEntity newArticle = new NavigationEntity("Yeni Makale", "/yeni-makale", anonymousEnabled, 4);
        NavigationEntity articleManagement = new NavigationEntity("Makale Yönetimi", "/makale-yonetimi", anonymousEnabled, 6);
        NavigationEntity logOut = new NavigationEntity("Çıkış", "/logout", anonymousEnabled, 7);

        NavigationEntity[] navBarContent = {mainPage, newArticle, articleManagement, logOut};

        for (NavigationEntity content : navBarContent) {
            navigationService.findOrCreateNavigationEntry(content.getName(), content.getLink(),
                    content.isAnonymousEnabled(), content.getViewOrder());
        }

        return true;
    }

}
