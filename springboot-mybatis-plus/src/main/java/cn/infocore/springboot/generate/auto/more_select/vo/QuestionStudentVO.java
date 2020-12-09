package cn.infocore.springboot.generate.auto.more_select.vo;

import cn.infocore.springboot.generate.auto.more_select.domain.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/9 15:53
 * @Description tb_student 与 tb_question 表联合查询返回的实体对象
 */
@Data
public class QuestionStudentVO extends Student implements Serializable {
    private static final long serialVersionUID = -4003507881583320902L;

    /**
     * 内容
     */
    private String content;

    /**
     * 最后修改日期
     */
    private Date lastModification;
}
