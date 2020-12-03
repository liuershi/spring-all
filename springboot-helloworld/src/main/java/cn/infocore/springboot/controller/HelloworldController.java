package cn.infocore.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 9:48
 * @Description
 */
@RestController
public class HelloworldController {

    @RequestMapping(value = "/helloworld")
    public String helloWorld(){
        return "Hello world";
    }
}
