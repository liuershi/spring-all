package cn.infocore.redis.example.mapper;

import cn.infocore.redis.example.core.CustomizeCache;
import cn.infocore.redis.example.domain.EmpDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/19 9:58
 * @Description
 */
@CacheNamespace(implementation = CustomizeCache.class, eviction = CustomizeCache.class)
// 说明：该注解的作用是将该mapper下产生的缓存绑定到UserMapper下，一般应用在多表联合查询时，其中一表数据改变后只会影响
//      对应mapper的缓存，而联合查询缓存中残留着旧的数据，此时全部绑定到UserMapper下，只要UserMapper改变那么多表缓存都会清除
@CacheNamespaceRef(value = UserMapper.class)
public interface EmpMapper extends BaseMapper<EmpDO> {
}
