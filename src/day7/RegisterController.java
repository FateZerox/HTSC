package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @GetMapping("/register")
    private String register(){
        return "register";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean register(@RequestBody JSONObject requestBody){
        if(requestBody.get("username").equals("admin"))
            return false;
        else
            return true;

        //return("admin".equals(requestBody.get("username")) && "123456".equals(requestBody.get("password")));
    }
}