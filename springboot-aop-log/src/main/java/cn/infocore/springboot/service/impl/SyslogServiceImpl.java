package cn.infocore.springboot.service.impl;

import cn.infocore.springboot.dao.SyslogDao;
import cn.infocore.springboot.domain.SysLogDO;
import cn.infocore.springboot.service.SyslogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 19:13
 * @Description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SyslogServiceImpl extends ServiceImpl<SyslogDao, SysLogDO> implements SyslogService {
}
