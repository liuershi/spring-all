package cn.infocore.redis.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 10:51
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.infocore.redis.example.mapper")
@EnableCaching
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
