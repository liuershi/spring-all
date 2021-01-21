package cn.infocore.redis.example.service.impl;

import cn.infocore.redis.example.domain.EmpDO;
import cn.infocore.redis.example.mapper.EmpMapper;
import cn.infocore.redis.example.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/19 9:59
 * @Description
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, EmpDO> implements EmpService {
}
