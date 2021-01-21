package cn.infocore.redis.example.service.impl;

import cn.infocore.redis.example.domain.UserDO;
import cn.infocore.redis.example.mapper.UserMapper;
import cn.infocore.redis.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 17:02
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
}
