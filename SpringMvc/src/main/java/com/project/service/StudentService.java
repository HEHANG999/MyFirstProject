package com.project.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stu")
public class StudentService {

    @RequestMapping("test")
    public String test(){
        return "/index.jsp";
    }
}
