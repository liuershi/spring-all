package cn.infocore.springboot.redis.controller;

import cn.infocore.springboot.redis.domain.UserDO;
import cn.infocore.springboot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/15 16:32
 * @Description
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("listAll")
    public List<UserDO> listAll() {
        return userService.list();
    }

    @GetMapping("list/{id}")
    public UserDO listById(@PathVariable("id") long id) {
        return userService.getById(id);
    }

    @PostMapping("save")
    public boolean save(UserDO userDO){
        return userService.save(userDO);
    }
}