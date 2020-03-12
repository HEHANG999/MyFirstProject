package com.project.service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentService {

    @RequestMapping("test")
    public String test(){
        return "/index.jsp";
    }


    @RequestMapping("getStu")
    @ResponseBody
    public String stu(){

        return "85878";
    }


}
