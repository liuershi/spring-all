package cn.infocore.excel.service.impl;

import cn.infocore.excel.domain.UserDO;
import cn.infocore.excel.mapper.UserMapper;
import cn.infocore.excel.service.UserService;
import cn.infocore.excel.util.ExcelUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:47
 * @Description
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public void exportExcel(HttpServletResponse response) {
        List<UserDO> users = this.list();

        // 定义表标题
        String title = "用户列表";
        // 定义列名
        String[] lines = new String[]{"序号", "用户名", "年龄","密码", "性别", "昵称", "出生年月"};
        // 定义表内容
        ExcelUtil excelUtil = new ExcelUtil();

        List<Object[]> dataList = new ArrayList<>();

        users.forEach(user -> {
            Object[] objects = new Object[lines.length];
            objects[0] = user.getId();
            objects[1] = user.getName();
            objects[2] = user.getAge();
            objects[3] = user.getPassword();
            objects[4] = user.getSex();
            objects[5] = user.getNickName();
            objects[6] = user.getBir();
            dataList.add(objects);
        });

        try {
            String fileName = new String("用户数据表.xls".getBytes(), "ISO8859-1");
            excelUtil.exportExcel(title, lines, dataList, fileName, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
