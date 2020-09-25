package com.wang.controller;

import com.wang.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello!";
    }

    //只要我们的接口中, 返回值中存在实体类, 他就会被扫描到Swagger中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    //Operation接口
    //Get请求中, 如果添加了字段的注释@ApiParam, Swagger无法测试
    @ApiOperation("Hello2接口")
    @GetMapping("/hello2")
    public String hello2(String username) {
        return "hello " + username;
    }

    //Operation接口
    @ApiOperation("Hello3接口")
    @PostMapping("/hello3")
    public User hello3(@ApiParam("用户") User user) {
        return user;
    }
}
