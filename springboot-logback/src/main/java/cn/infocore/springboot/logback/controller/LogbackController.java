package cn.infocore.springboot.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/18 18:02
 * @Description
 */
@RestController
@RequestMapping("logback")
public class LogbackController {

    private static final Logger log = LoggerFactory.getLogger(LogbackController.class);

    @GetMapping("attempt")
    public String attemptLogback(){
        if (log.isDebugEnabled()) {
            log.debug("attempt logback success");
        }
        return "ok";
    }
}
