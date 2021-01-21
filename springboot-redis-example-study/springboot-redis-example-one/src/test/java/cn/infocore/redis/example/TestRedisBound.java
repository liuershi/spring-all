package cn.infocore.redis.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 15:16
 * @Description
 */
@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestRedisBound {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 使用Bound绑定对象简化操作，即多个操作之间不需要每次都制定操作key
     */
    @Test
    public void testBound(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        BoundValueOperations<Object, Object> zhangsan = redisTemplate.boundValueOps("zhangsan");
        zhangsan.set("我是 zhangsan");
        zhangsan.append(" 你是一个好人");
        log.info("zhangsan is :{}", zhangsan.get());
        log.info("zhangsan length :{}", zhangsan.size());
    }
}
