package cn.infocore.excel.domain;

import cn.infocore.excel.myenum.SexEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:43
 * @Description
 */
@Data
@Accessors(chain = true)
@TableName("User")
public class UserDO implements Serializable {
    /**主键ID*/
    @TableId
    private Long id;
    /**姓名*/
    private String name;
    /**年龄*/
    private Integer age;
    /**密码*/
    private String password;
    /**性别*/
    private SexEnum sex;
    /**昵称*/
    private String nickName;
    /**出生日期*/
    private Date bir;
}
