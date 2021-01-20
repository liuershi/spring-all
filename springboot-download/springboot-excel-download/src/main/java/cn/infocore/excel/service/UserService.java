package cn.infocore.excel.service;

import cn.infocore.excel.domain.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:46
 * @Description
 */
public interface UserService extends IService<UserDO> {

    /**
     * 导出Excel接口
     * @param response
     */
    void exportExcel(HttpServletResponse response);
}
