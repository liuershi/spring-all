package cn.infocore.springboot.service;

import cn.infocore.springboot.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
public interface UserService extends IService<UserDO> {

    /**
     * 自定义方法
     * @param id
     * @return
     */
    UserDO findById(long id);

    /**
     * 自定义一个查询方法，所有mapper继承后都能继承这个方法
     * @return
     */
    List<UserDO> customizeFindAll();
}
