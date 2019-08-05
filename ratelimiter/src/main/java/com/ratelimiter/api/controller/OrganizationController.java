package com.ratelimiter.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrganizationController {

    @RequestMapping("/api/v1/organizations")
    Map<String,String> organizations(){

        Map<String, String> data = new HashMap<>();

        data.put("key1","organizations data");

        return data;
    }
}
