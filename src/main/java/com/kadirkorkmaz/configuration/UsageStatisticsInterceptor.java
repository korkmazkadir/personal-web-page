package com.kadirkorkmaz.configuration;

import com.kadirkorkmaz.database.entity.UsageStatisticsEntity;
import com.kadirkorkmaz.service.UsageStatisticsService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class UsageStatisticsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UsageStatisticsService usageStatisticsService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        String userName = request.getUserPrincipal() != null ?  request.getUserPrincipal().getName() : "anonymous_user";
        String sessionId = request.getSession().getId();
        String requestURI = request.getRequestURI();

        Long articleId = null;

        if (modelAndView != null) {
            articleId = (Long) modelAndView.getModel().get("articleIdForUsage");
        }

        UsageStatisticsEntity usage = new UsageStatisticsEntity(userName,sessionId, requestURI, articleId);
        usageStatisticsService.saveUsageStatistic(usage);

        super.postHandle(request, response, handler, modelAndView);
    }

}
