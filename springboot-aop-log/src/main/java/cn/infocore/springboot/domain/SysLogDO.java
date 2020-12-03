package cn.infocore.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 18:59
 * @Description 实体类
 */
@Data
@TableName(value = "SYS_LOG")
public class SysLogDO implements Serializable {
    /**
     * 主键自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String operation;
    private Long time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
