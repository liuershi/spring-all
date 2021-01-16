package cn.infocore.springboot.redis;

import cn.infocore.springboot.redis.domain.UserDO;
import cn.infocore.springboot.redis.mapper.UserMapper;
import cn.infocore.springboot.redis.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/10 17:03
 * @Description
 */
@SpringBootTest(classes = {ApplicationRedisMain.class})
@RunWith(SpringRunner.class)
@Slf4j
public class TestRedis {

    @Autowired
    private SqlSessionFactory factory;

    @Autowired
    private UserService service;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 通过 SqlSession 获取到的mapper
     */
    @Test
    public void test1(){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        IntStream.range(0,3).forEach(i -> System.out.println(mapper.selectById(1)));
    }

    /**
     * 使用注入的 mapper
     *
     *      需要注意的是，当不使用 @Transactional 注解时，每次调用getById方法每次都会创建一个 sqlSession
     *      ，使用完毕后关闭 sqlSession，导致每次都会去数据库中查询；
     *      而使用 @Transactional 注解后，即任务多次操作是在一个事物管理中，只会创建一个 sqlSession，所以后面的
     *      相同查询则会从缓存中取，不会查询数据库
     */
    @Transactional
    @Test
    public void test2(){
        IntStream.range(0,3)
                .forEach(i -> {
                    UserDO userDO = service.getById(1L);
                    System.out.println(userDO);
                });
    }

    @Test
    public void test3(){
        stringRedisTemplate.opsForValue().set("k1", "v1");
        System.out.println(stringRedisTemplate.opsForValue().get("k1"));
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 先查询
        UserDO userDO = mapper.selectById(2L);
        System.out.println(userDO);
        // 执行修改操作
        UserDO user = new UserDO();
        user.setId(3L);
        user.setName("王五test");
        user.setPassword("89898989");
        mapper.updateById(user);
        // 再执行相同查询
        UserDO userDOTwo = mapper.selectById(2L);
        System.out.println(userDOTwo);
    }

    /**
     * 通过两个不同的SqlSession执行相同的sql语句
     */
    @Test
    public void moreSqlSession(){
        SqlSession sqlSessionOne = factory.openSession();
        SqlSession sqlSessionTwo = factory.openSession();

        UserMapper mapperOne = sqlSessionOne.getMapper(UserMapper.class);
        UserMapper mapperTwo = sqlSessionTwo.getMapper(UserMapper.class);

        System.out.println(mapperOne.selectById(1L));
        System.out.println(mapperTwo.selectById(1L));
    }
}
