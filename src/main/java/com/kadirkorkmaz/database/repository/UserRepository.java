package com.kadirkorkmaz.database.repository;

import com.kadirkorkmaz.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findOneByUserName (String userName);
    UserEntity findOneById (long id);
}
