package com.kadirkorkmaz.database.repository;

import com.kadirkorkmaz.database.entity.UsageStatisticsEntity;
import org.springframework.data.repository.CrudRepository;


public interface UsageStatisticsRepository extends CrudRepository<UsageStatisticsEntity, Long>{
    
}
