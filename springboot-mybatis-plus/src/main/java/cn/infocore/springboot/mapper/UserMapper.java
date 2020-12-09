package cn.infocore.springboot.mapper;

import cn.infocore.springboot.domain.UserDO;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 22:52
 * @Description User 对象的 Mapper 接口
 */
public interface UserMapper extends MyMapper<UserDO> {

    /**
     * 自定义方法
     * @param id
     * @return
     */
    UserDO findById(long id);
}
