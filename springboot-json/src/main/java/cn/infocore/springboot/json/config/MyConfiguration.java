package cn.infocore.springboot.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/17 20:05
 * @Description
 */
@Configuration
public class MyConfiguration {

    /**
     * 使用自定义的ObjectMapper，设置序列化的日期格式(也可以在日期属性上加上@JsonFormat注解)
     * @return
     */
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }
}
