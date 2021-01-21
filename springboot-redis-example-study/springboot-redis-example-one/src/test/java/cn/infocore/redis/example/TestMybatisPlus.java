package cn.infocore.redis.example;

import cn.infocore.redis.example.domain.EmpDO;
import cn.infocore.redis.example.domain.UserDO;
import cn.infocore.redis.example.mapper.EmpMapper;
import cn.infocore.redis.example.mapper.UserMapper;
import cn.infocore.redis.example.service.EmpService;
import cn.infocore.redis.example.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 17:03
 * @Description 测试mybatis-plus
 */
@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestMybatisPlus {

    @Autowired
    private UserService service;

    @Test
    public void testSave(){
        UserDO userDO = new UserDO().setId(UUID.randomUUID().toString())
                .setName("张三")
                .setAge(25)
                .setAddress("浙江省宁波市")
                .setBir(new Date());
        boolean save = service.save(userDO);
        log.info("save user id success ：{}", save);
    }

    @Test
    public void testListAll(){
        List<UserDO> list = service.list();
        list.forEach(item -> log.info("item : {}", item));

        log.info("===========================");

        List<UserDO> list2 = service.list();
        list2.forEach(item -> log.info("item : {}", item));
    }


    @Test
    public void testListById(){
        log.info("user result : {}", service.getById(1));
        log.info("=========================");
        log.info("user result : {}", service.getById(1));
    }

    @Test
    public void testListByName(){
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new UserDO().setName("张三"));
        service.list(wrapper).forEach(item -> log.info("item:{}", item));
    }

    @Test
    public void testDeleteByName(){
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        service.remove(wrapper.eq("name", "张三"));
    }

    @Test
    public void testUpdateByName(){
        UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("address", "湖北省武汉市").eq("name", "张三");
        service.update(updateWrapper);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++  emp  +++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Autowired
    private EmpService empService;

    @Test
    public void testEmpSave(){
        boolean save = empService.save(new EmpDO().setName("张三老师"));
        log.info("save emp is result:{}", save);
    }

    @Test
    public void testEmpListAll() {
        empService.list().forEach(item -> log.info("item is :{}", item));
    }

    @Test
    public void testEmpDelete(){
        empService.removeById(1L);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++  md5  +++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * 使用MD5加密缓存的key，优化key
     * 说明：
     *      1.MD5可以对一切文件和数据进行加密，得到一个32位的16进制字符串
     *      2.不同内容进行MD5加密后得到的内容一定是不同的，通过这个特性可以判断两个文件的内容是否相同
     *      3.相同内容多次加密后的得到的结果始终一致
     */
    @Test
    public void testMd5(){
        String key = "-408532156:3421316359:cn.infocore.redis.example.mapper.EmpMapper.selectList:0:2147483647:SELECT  id,name  FROM emp:MybatisSqlSessionFactoryBean";
        String md5 = DigestUtils.md5DigestAsHex(key.getBytes());
        log.info("key to MD5 :{}", md5);
    }
}
