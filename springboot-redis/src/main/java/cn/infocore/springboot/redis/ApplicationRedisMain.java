package cn.infocore.springboot.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/10 17:01
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.infocore.springboot.redis.mapper")
@EnableCaching
public class ApplicationRedisMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRedisMain.class, args);
    }
}
