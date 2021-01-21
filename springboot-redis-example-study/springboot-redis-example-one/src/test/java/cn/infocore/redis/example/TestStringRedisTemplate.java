package cn.infocore.redis.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;
import java.util.UUID;


/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 10:52
 * @Description
 */
@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestStringRedisTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString(){
        String name = stringRedisTemplate.opsForValue().get("name");
        log.info("name:{}", name);

        stringRedisTemplate.opsForValue().set("age", "25");
        stringRedisTemplate.opsForValue().set("address", "hangzhou");
        stringRedisTemplate.opsForValue().set("sex", "man");

        Set<String> keys = stringRedisTemplate.keys("*");
        keys.forEach(key -> log.info("key:{}", key));

        Boolean hasName = stringRedisTemplate.hasKey("name");
        log.info("has name:{}", hasName);
    }

    @Test
    public void tesList(){
        stringRedisTemplate.delete("names");
        stringRedisTemplate.opsForList().leftPush("names", "张威");
        stringRedisTemplate.opsForList().leftPushAll("names", "张三", "李四", "王五", "王五", "赵六");
        log.info("names length:{}" , stringRedisTemplate.opsForList().size("names"));
        stringRedisTemplate.opsForList().range("names", 0, -1).forEach(item -> log.info("item info :{}", item));
    }

    @Test
    public void testSet(){
        stringRedisTemplate.opsForSet().add("movies", "复仇者联盟", "速度与激情", "姜子牙", "大圣归来", "西游记", "速度与激情");
        log.info("set length : {}", stringRedisTemplate.opsForSet().size("movies"));
        stringRedisTemplate.opsForSet().members("movies").forEach(item -> log.info("set item is :{}", item));
    }

    @Test
    public void testZSet(){
        stringRedisTemplate.opsForZSet().add("fraction", "优秀", 100);
        stringRedisTemplate.opsForZSet().add("fraction", "普通", 80);
        stringRedisTemplate.opsForZSet().add("fraction", "不及格", 20);
        stringRedisTemplate.opsForZSet().add("fraction", "及格", 60);

        stringRedisTemplate.opsForZSet().range("fraction", 0, -1).forEach(item -> log.info("sort fraction : {}", item));
    }

    @Test
    public void testHash(){
        stringRedisTemplate.opsForHash().put("001", "name", "张三");
        stringRedisTemplate.opsForHash().put("001", "age", "25");
        stringRedisTemplate.opsForHash().put("001", "sex", "男");
        stringRedisTemplate.opsForHash().put("001", "address", "杭州");
        stringRedisTemplate.opsForHash().entries("001").entrySet()
                .forEach(map -> log.info("key:{}  value:{}", map.getKey(), map.getValue()));

    }

    @Test
    public void testUUID(){
        log.info("random UUID length : {}", UUID.randomUUID().toString().length());
    }
}