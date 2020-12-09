package cn.infocore.springboot.controller;

import cn.infocore.springboot.domain.UserDO;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/8 15:44
 * @Description ActiveRecord（https://en.wikipedia.org/wiki/Active_record_pattern）适合结构简单的操作，一般只对单表操作
 */
@RestController
@RequestMapping(value = "activeRecord")
public class ActiveRecordController {

    @GetMapping("list/{id}")
    public UserDO getUserById(@PathVariable("id") long id) {
        return new UserDO().setId(id).selectById();
    }

    @PostMapping("save")
    public boolean save(UserDO userDO) {
        return userDO.insert();
    }

    @DeleteMapping(value = "delete/{id}")
    public boolean deleteById(@PathVariable("id") long id) {
        return new UserDO().setId(id).deleteById();
    }

    @PutMapping("/update")
    public boolean updateById(UserDO userDO){
        return userDO.updateById();
    }
}
