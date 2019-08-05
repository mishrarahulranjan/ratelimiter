package com.ratelimiter.api.ratelimit;
/**
 * RateLimiter
 */
public class RateLimiter {

    private int capacity;

    long lastRefillTimeStamp;

    int availableTokens;

    public RateLimiter(int capacity){
        this.capacity = capacity;
        lastRefillTimeStamp = System.currentTimeMillis();
        this.availableTokens = capacity;
    }

    public long getAvailableTokens(){
        return this.availableTokens;
    }

    public boolean consume(){

        refill();

        if(availableTokens > 0)
        {
            --availableTokens;
            return true;
        }
        else
        {
            return false;
        }
    }

    private void refill(){

        long now = System.currentTimeMillis();

        // check if 1 hr is completed
        if((now-lastRefillTimeStamp)> 1000*60*60)
        {
           availableTokens = capacity;
           lastRefillTimeStamp = now;

        }
    }

}