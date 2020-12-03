package cn.infocore.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 11:18
 * @Description
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * Spring3.1 版本，为了满足不同环境注册不同的 Bean
     * @return
     */
    @Bean
    @Profile(value = {"DEV"})
    public DataSource devDataSource(){
        // ... 单机 MySQL
        return null;
    }

    @Bean
    @Profile(value = {"PROD"})
    public DataSource prodDataSource(){
        // ... 集群 MySQL
        return null;
    }
}
