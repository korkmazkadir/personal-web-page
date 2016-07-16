package com.kadirkorkmaz.service;

import com.kadirkorkmaz.database.entity.UserEntity;
import com.kadirkorkmaz.database.repository.UserRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    
    public UserEntity getUser(String userName){
        return userRepository.findOneByUserName(userName);
    }
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserEntity userFromDb = userRepository.findOneByUserName(string);
        
        if (userFromDb == null) {
            return null;
        }
        
        String grantAutString = userFromDb.getRoles().stream().map(u -> u.getRole().toString()).collect(Collectors.joining(","));
        User user = new User(userFromDb.getUserName(), userFromDb.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(grantAutString));
        return user;
    }

}
