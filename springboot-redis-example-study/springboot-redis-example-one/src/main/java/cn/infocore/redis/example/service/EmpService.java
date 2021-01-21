package cn.infocore.redis.example.service;

import cn.infocore.redis.example.domain.EmpDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/19 9:58
 * @Description
 */
@Transactional
public interface EmpService extends IService<EmpDO> {
}
