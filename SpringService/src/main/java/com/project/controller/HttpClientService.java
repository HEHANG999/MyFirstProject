package com.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userE")
public class HttpClientService {

    @RequestMapping("ser")
    public String ser(){

        return "hello";
    }

}
