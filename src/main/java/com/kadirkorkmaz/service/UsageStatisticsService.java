
package com.kadirkorkmaz.service;

import com.kadirkorkmaz.database.entity.UsageStatisticsEntity;
import com.kadirkorkmaz.database.repository.UsageStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsageStatisticsService {
    
    @Autowired
    UsageStatisticsRepository usageStatistcsRepository;
    
    public void saveUsageStatistic(UsageStatisticsEntity usage){
        usageStatistcsRepository.save(usage);
        return;
    }

}
