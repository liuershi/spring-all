package cn.infocore.springboot.redis.mapper;

import cn.infocore.springboot.redis.core.MybatisRedisCache;
import cn.infocore.springboot.redis.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/10 17:04
 * @Description
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface UserMapper extends BaseMapper<UserDO> {
}
