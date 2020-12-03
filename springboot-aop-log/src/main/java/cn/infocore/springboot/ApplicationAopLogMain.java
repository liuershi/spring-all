package cn.infocore.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 17:45
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.infocore.springboot.dao")
public class ApplicationAopLogMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationAopLogMain.class, args);
    }
}
