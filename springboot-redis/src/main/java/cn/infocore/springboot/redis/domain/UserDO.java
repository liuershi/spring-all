package cn.infocore.springboot.redis.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 22:50
 * @Description
 */
@Data
@TableName(value = "tb_user")
@Accessors(chain = true)
/**
 * 说明：extends Model<UserDO>
 *     作用是为了使用ActiveRecord，虽然使用它之后没用到UserMapper，但是ActiveRecord的底层
 *     还是使用的UserMapper实现功能的，所以不能删除UserMapper
 */
public class UserDO extends Model<UserDO> implements Serializable {
    /**
     * 设置主键 ID 的生成策略，当前生成策略为自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;

    /**
     * select = false: 查询时不查询该属性
     */
    @TableField(select = false, fill = FieldFill.INSERT_UPDATE)
    private String password;
    private String name;
    private Integer age;

    /**
     * 属性与表中字段名不一致时
     */
    @TableField(value = "email")
    private String mail;

    /**
     * exist = false：表中不存在的字段时
     *
     * @NotBlank： JSR303的规范
     */
    @TableField(exist = false)
    @NotBlank(message = "地址不能为空")
    private String address;

    @TableLogic
    private Integer deleted;
}
