package cn.infocore.redis.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/21 15:11
 * @Description 交由redis管理session的配置
 */
@Configuration
// 开启session交由redis托管的注解
// 设置session过期时间，单位是秒，默认30分钟
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 35)
public class SessionManagerConfiguration {
}
