package cn.infocore.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:41
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.infocore.excel.mapper")
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
