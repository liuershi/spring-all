package cn.infocore.redis.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/18 14:19
 * @Description
 */
@Data
@Accessors(chain = true)
@TableName("User")
public class UserDO implements Serializable {
    private static final long serialVersionUID = -6762208437957725247L;

    private String id;
    private String name;
    private Integer age;
    private String address;
    private Date bir;
}
