package com.ratelimiter.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DeveloperController {

    @RequestMapping("/api/v1/developers")
    Map<String,String> developers(){

        Map<String, String> data = new HashMap<>();

        data.put("key1","developers data");

        return data;
    }
}
