package com.kadirkorkmaz.configuration;

import com.kadirkorkmaz.database.entity.UserEntity;
import com.kadirkorkmaz.database.entity.UserRole;
import com.kadirkorkmaz.database.entity.UserRoleEnum;
import com.kadirkorkmaz.database.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author kadir
 */
@Configuration
public class DefaultUserConfiguration {

    private static final Logger logger = Logger.getLogger(DefaultUserConfiguration.class);

    @Autowired
    UserRepository userRepo;

    @Value("${defaut.user.name}")
    String name;
    @Value("${defaut.user.surname}")
    String surname;
    @Value("${defaut.user.username}")
    String userName;
    @Value("${defaut.user.password}")
    String password;

    @Bean
    public UserEntity registerDefaultUser() {

        UserEntity defaultUser = userRepo.findOneByUserName(userName);

        if (defaultUser != null) {
            logger.info("Default user is alreadey registered.");
            return defaultUser;
        }

        logger.info("Creating default user.");
        
        defaultUser = new UserEntity(name, surname, userName, password);

        defaultUser.addToRoles(new UserRole(UserRoleEnum.ADMIN));
        defaultUser.addToRoles(new UserRole(UserRoleEnum.WRITER));
        
        logger.info("Seaving default user.");
        
        userRepo.save(defaultUser);

        return defaultUser;
    }
}
