package cn.infocore.springboot.redis.service;

import cn.infocore.springboot.redis.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/15 16:30
 * @Description
 */
public interface UserService extends IService<UserDO> {
}
