package cn.infocore.redis.example;

import cn.infocore.redis.example.domain.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 14:16
 * @Description 支持泛型，可以操作对象
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 使用RedisTemplate时，默认的序列化机制使用的是jdk的，即 {@link JdkSerializationRedisSerializer}，不管是key还是value
     * 都采用的这种方式，这种方式在实际开发存在一个问题，就是在开发中我们使用一个key存入redis，可能还想根据这个值取出来，由于jdk的序列化机制，
     * key序列化的值可能是乱码的，此时只能在代码中取出来，因此对于这种情况我们可以单独的设置key值的序列化方式为 {@link StringRedisSerializer},
     * 即以字符串的方式序列化，保证其序列化之后的保存形式不变
     */
    @Test
    public void testUser(){
        // 单独设置key的序列化方式为StringRedisSerializer，保证其在console显示时保持原有
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        UserDO userDO = new UserDO().setId(UUID.randomUUID().toString()).setName("张三").setAge(25).setAddress("浙江省杭州市萧山区").setBir(new Date());
        redisTemplate.opsForValue().set("user", userDO);

        UserDO result = (UserDO)redisTemplate.opsForValue().get("user");
        log.info("result:{}", result);

        log.info("key default Serialization ：{}", redisTemplate.getKeySerializer());
        log.info("value default Serialization ：{}", redisTemplate.getValueSerializer());
    }

    /**
     * 说明：
     *      redis中此时存在 key-value 为 name-zhangsan 的键值对，但是查询出来为null；
     *      由于使用的是RedisTemplate，它在get或set时都会将key序列化，所以实际上去redis
     *      中查询时用的key是序列化后的值，所以查询不到。
     */
    @Test
    public void test(){
        Object name = redisTemplate.opsForValue().get("name");
        log.info("name:{}", name);

        redisTemplate.delete("user");
    }
}
