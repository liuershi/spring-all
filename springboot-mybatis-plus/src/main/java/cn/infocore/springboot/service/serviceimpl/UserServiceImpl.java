package cn.infocore.springboot.service.serviceimpl;

import cn.infocore.springboot.domain.UserDO;
import cn.infocore.springboot.mapper.UserMapper;
import cn.infocore.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 22:59
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserDO findById(long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<UserDO> customizeFindAll() {
        return baseMapper.customizeFindAll();
    }
}
