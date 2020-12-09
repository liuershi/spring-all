package cn.infocore.springboot.config;

import cn.infocore.springboot.injector.MyInjector;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/5 23:55
 * @Description分页插件配置
 */
@Configuration
// 记住，要扫描我们定义的mapper接口
@MapperScan(basePackages = "cn.infocore.springboot.mapper")
//@MapperScan(basePackages = "cn.infocore.springboot.generate.auto.more_select.mapper")
public class PagePluginConfig {

    /**
     * mybatis-plus version:3.4.0 开始的分页插件配置
     *      如果不配置插件，虽然可以使用分页，但是有些问题，比如总记录数异常等等。
     * @return
     */
    @Bean
    public MybatisPlusInterceptor paginationInnerInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        ArrayList<InnerInterceptor> innerInterceptors = new ArrayList<>();
        innerInterceptors.add(paginationInnerInterceptor);
        mybatisPlusInterceptor.setInterceptors(innerInterceptors);
        return mybatisPlusInterceptor;

    }


    /**
     * 将自定义的SQL注入器注入容器
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new MyInjector();
    }
}
