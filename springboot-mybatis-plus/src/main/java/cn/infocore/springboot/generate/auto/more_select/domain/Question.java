package cn.infocore.springboot.generate.auto.more_select.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外键ID(学生ID)
     */
    private Long studentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布日期
     */
    private Date date;

    /**
     * 最后修改日期
     */
    private Date lastModification;


}
