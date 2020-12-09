package cn.infocore.springboot.generate.auto.more_select.mapper;

import cn.infocore.springboot.generate.auto.more_select.domain.Student;
import cn.infocore.springboot.generate.auto.more_select.vo.QuestionStudentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 自定义联合查询SQL
     * @param page
     * @param wrapper
     * @return
     */
    List<QuestionStudentVO> queryStudentInfoById(IPage<QuestionStudentVO> page, @Param(Constants.WRAPPER) QueryWrapper<Student> wrapper);
}
