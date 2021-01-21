package cn.infocore.redis.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/19 9:57
 * @Description
 */
@Data
@Accessors(chain = true)
@TableName(value = "emp")
public class EmpDO implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
}
