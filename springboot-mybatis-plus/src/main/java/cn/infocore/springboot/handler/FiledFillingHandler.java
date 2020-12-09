package cn.infocore.springboot.handler;

import cn.infocore.springboot.myenum.SexEnum;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/8 20:15
 * @Description 字段填充处理器
 */
@Component
public class FiledFillingHandler implements MetaObjectHandler {
    /**
     * 它的作用是，在更新或者插入数据时，可以对字段值进行填充，需要在实体类对应字段
     * 上加上 @TableField(fill = FieldFill.INSERT_UPDATE) 注解，该注解说明
     * 此时被注解修饰的属性在 insert 或 update 时都能被触发
     *      例：password 的值前端传过来为空时，我们可以为其设置一个默认的密码填充
     */


    /**
     * insert 操作时触发
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String password = (String)getFieldValByName("password", metaObject);
        if (StringUtils.isEmpty(password)) {
            setFieldValByName("password", "88888888", metaObject);
        }
    }

    /**
     * update操作时触发
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String password = (String)getFieldValByName("password", metaObject);
        if (StringUtils.isEmpty(password)) {
            setFieldValByName("password", "66666666", metaObject);
        }
    }
}
