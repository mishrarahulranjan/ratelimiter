package com.ratelimiter.api.ratelimit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ratelimiter.api.cache.InMemoryCache;
import com.ratelimiter.api.ratelimit.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Date 2019/08/04
 * @version 1.0
 */
@Component
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {

        String requestURI = request.getRequestURI();
        String userId = ServletRequestUtils.getStringParameter(request, "userId", "");
        String key = requestURI+"."+userId;
        RateLimiter rateLimiter = (RateLimiter)InMemoryCache.get(key);
        boolean status = false;
        if(null != rateLimiter){
            status = rateLimiter.consume();
        }

        if(!status){
            response.getWriter().write("too many Request");
        }

        return status;
    }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response,
                               Object object, ModelAndView model)
                throws Exception {
            System.out.println("In postHandle");
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                    Object object, Exception arg3)
                throws Exception {
            System.out.println("In afterCompletion ");
        }
}