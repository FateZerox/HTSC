package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String register(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean login(@RequestBody JSONObject requestBody){
        return("admin".equals(requestBody.get("username")) && "123456".equals(requestBody.get("password")));
    }
}