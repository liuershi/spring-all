package cn.infocore.excel;

import cn.infocore.excel.domain.UserDO;
import cn.infocore.excel.myenum.SexEnum;
import cn.infocore.excel.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 13:49
 * @Description
 */
@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private UserService service;

    @org.junit.Test
    public void save(){
        UserDO userDO = new UserDO().setName("李四").setAge(28).setPassword("admin123").setSex(SexEnum.WOMAN).setNickName("小小君子").setBir(new Date());
        service.save(userDO);
    }

    @org.junit.Test
    public void list(){
        service.list().forEach(System.out::println);
    }
}
