package cn.infocore.redis.example.core;

import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 18:41
 * @Description
 */
@Slf4j
public class CustomizeCache implements Cache {
    /**
     * 读写锁，控制并发
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String id;

    public CustomizeCache(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    /**
     * key值默认太冗余且长，可以使用MD5加密优化
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        key = CommonUtil.getKeyToMD5(key.toString());
        log.info("cache key : {}", key);
        log.info("cache value : {}", value);
        redisTemplate = getRedisTemplate();
        if (value != null) {
            this.redisTemplate.opsForHash().put(id, key, value);
        }
    }

    @Override
    public Object getObject(Object key) {
        redisTemplate = getRedisTemplate();
        if (key != null) {
            return redisTemplate.opsForHash().get(id, CommonUtil.getKeyToMD5(key.toString()));
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (key != null) {
            return redisTemplate.delete(key.toString());
        }
        return null;
    }

    /**
     * 实际上不管是删除还是清空方法都会执行clear方法，并不会执行removeObject
     */
    @Override
    public void clear() {
        if (log.isDebugEnabled()) {
            log.debug("start clear cache");
        }
        redisTemplate = getRedisTemplate();

        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        redisTemplate = getRedisTemplate();
        // 获取hash中的key,value数量
        return redisTemplate.opsForHash().size(id).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private RedisTemplate<String, Object> getRedisTemplate(){
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
        }
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
