package cn.infocore.springboot.controller;

import cn.infocore.springboot.domain.UserDO;
import cn.infocore.springboot.mapper.UserMapper;
import cn.infocore.springboot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 23:02
 * @Description 用户模块控制层
 */
@RestController
@RequestMapping(value = "user")
@Slf4j(topic = "UserController")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 查询所有的用户
     * @return
     */
    @GetMapping(value = "list")
    public List<UserDO> listAllUsers(){
        QueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>().orderByDesc("age").orderByDesc("id");
        return service.list(wrapper);
    }

    /**
     * 批量查询
     * @return
     */
    @GetMapping(value = "list/ids")
    public List<UserDO> listByIds(@RequestBody List<Long> ids){
        return service.listByIds(ids);
    }

    /**
     * 添加用户
     *
     *   注意：mybatis-plus 在插入数据成功后会将数据回填到实体对象中，
     *      所以，我们能通过实体类直接获取到自增的ID。
     *
     * @param userDO
     * @return
     */
    @PostMapping(value = "add")
    public boolean save(@Valid UserDO userDO){
        boolean save = service.save(userDO);
        log.info("user id :{}",userDO.getId());
        return save;
    }

    /**
     * 更新用户数据
     * @param userDO
     * @return
     */
    @PatchMapping(value = "update")
    public boolean updateById(UserDO userDO){
        return service.updateById(userDO);
    }

    /**
     * 根据年龄修改用户，两种方式：
     *      QueryWrapper
     *      UpdateWrapper
     *
     * @param userDO
     * @return
     */
    @PatchMapping(value = "/update/age")
    public boolean updateByAge(UserDO userDO) {
        // 1.QueryWrapper
//        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
//        wrapper.eq("age", userDO.getAge());


        // 2.UpdateWrapper
        UpdateWrapper<UserDO> wrapper = new UpdateWrapper<>();
        wrapper.eq("age", userDO.getAge()) // 条件
        .set("name", userDO.getName()).set("password", userDO.getPassword()); // 属性设置
        return service.update(null, wrapper);
    }

    /**
     * 删除用户(根据主键ID)
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete")
    public boolean deleteById(long id){
        return service.removeById(id);
    }

    /**
     * 删除用户(自定义删除条件)，使用
     * @param username
     * @param password
     * @return
     */
    @DeleteMapping(value = "delete/map")
    public boolean deleteByMap(String username, String password){
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", username);
        map.put("password", password);
        return service.removeByMap(map);
    }

    /**
     * 根据id批量删除数据
     * @param ids
     * @return
     */
    @DeleteMapping(value = "delete/ids")
    public boolean deleteByIds(@RequestBody List<Long> ids){
       return service.removeByIds(ids);
    }

    /**
     * 根据年龄统计数量(大于age的结果)
     * @return
     */
    @GetMapping(value = "list/count")
    public long listCountByAge(Integer age) {
        if (age == null){
            return service.count();
        }
        return service.count(new QueryWrapper<UserDO>().gt("age", age));
    }

    //======================================================
    //======================= 分页查询 ======================
    //======================================================
    @GetMapping("list/page")
    public List<UserDO> listPageUsers(Page<UserDO> page, UserDO userDO){
        page.setSize(3);
        IPage<UserDO> iPage = service.page(page, new QueryWrapper<>(userDO));
        log.info("总条数:{}", iPage.getTotal());
        return iPage.getRecords();
    }


    //======================================================
    //======================= 自定义mapper xml ==============
    //======================================================
    @GetMapping(value = "list/customize")
    public UserDO customizeMapperXmlById(long id) {
        return service.findById(id);
    }



    //======================================================
    //======================= 自定义SQL注入器 ==============
    //======================================================
    @GetMapping(value = "list/customize/injector")
    public List<UserDO> customizeISqlInjector() {
        return service.customizeFindAll();
    }
}
