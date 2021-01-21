package cn.infocore.redis.example.service;

import cn.infocore.redis.example.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 17:01
 * @Description
 */
@Transactional
public interface UserService extends IService<UserDO> {
}
