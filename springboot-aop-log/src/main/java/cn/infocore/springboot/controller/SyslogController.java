package cn.infocore.springboot.controller;

import cn.infocore.springboot.core.Log;
import cn.infocore.springboot.domain.SysLogDO;
import cn.infocore.springboot.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 19:15
 * @Description
 */
@RestController
@RequestMapping(value = "/syslog")
public class SyslogController {

    @Log(value = "测试保存系统日志1")
    @PostMapping(value = "/save1")
    public String save1(){
        return "保存成功";
    }

    @Log(value = "测试保存系统日志2")
    @PostMapping(value = "/save2")
    public String save2(String username) throws InterruptedException {
        System.out.println(username);
        TimeUnit.SECONDS.sleep(2);
        return null;
    }

    @Log(value = "测试保存系统日志3")
    @PostMapping(value = "/save3")
    public void save3(){
        throw new RuntimeException("error...");
    }
}
