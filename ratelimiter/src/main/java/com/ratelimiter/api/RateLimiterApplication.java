package com.ratelimiter.api;

import com.ratelimiter.api.cache.InMemoryCache;
import com.ratelimiter.api.ratelimit.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * Entry point
 */
@SpringBootApplication
public class RateLimiterApplication {

    private static String AVAILABLEAPI = "availableapi";

    private static String AVAILABLEUSER = "availableuser";

    public static void main(String[] args) {
        SpringApplication.run(RateLimiterApplication.class, args);
    }

    @Autowired
    private Environment env;

    @PostConstruct
    void init(){

        String availableApi = env.getProperty(AVAILABLEAPI);

        String availableUser = env.getProperty(AVAILABLEUSER);

        if(!StringUtils.isEmpty(availableApi)){
            String [] apiArray = StringUtils.split(availableApi,",");
            String [] userArray = StringUtils.split(availableUser,",");

            for (String api : apiArray)
            {
                for (String user : userArray){
                    String key = api+"."+user;
                    String apiLimit = env.getProperty(key);
                    if(StringUtils.isEmpty(availableApi)){
                        apiLimit = env.getProperty(api+".default");
                    }
                    try{
                        RateLimiter rateLimit;
                        if(!StringUtils.isEmpty(availableApi)){
                            rateLimit = new RateLimiter(Integer.parseInt(apiLimit));
                        }else{
                            rateLimit = new RateLimiter(100);
                        }
                        key = "/api/v1/"+key;
                        InMemoryCache.put(key,rateLimit);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

        }
    }

}
