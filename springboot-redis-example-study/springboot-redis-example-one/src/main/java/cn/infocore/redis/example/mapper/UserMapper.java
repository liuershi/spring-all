package cn.infocore.redis.example.mapper;

import cn.infocore.redis.example.core.CustomizeCache;
import cn.infocore.redis.example.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 16:59
 * @Description
 */
@CacheNamespace(implementation = CustomizeCache.class, eviction = CustomizeCache.class)
public interface UserMapper extends BaseMapper<UserDO> {
}
