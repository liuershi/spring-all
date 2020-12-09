package cn.infocore.springboot.generate.auto.more_select.service;

import cn.infocore.springboot.generate.auto.more_select.domain.Student;
import cn.infocore.springboot.generate.auto.more_select.vo.QuestionStudentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
public interface StudentService extends IService<Student> {

    List<QuestionStudentVO> queryStudentInfoById(IPage<QuestionStudentVO> page, @Param(Constants.WRAPPER) QueryWrapper<Student> wrapper);
}
