package cn.infocore.springboot.redis.service.impl;

import cn.infocore.springboot.redis.domain.UserDO;
import cn.infocore.springboot.redis.mapper.UserMapper;
import cn.infocore.springboot.redis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/15 16:30
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
}
