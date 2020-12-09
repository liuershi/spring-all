package cn.infocore.springboot.generate.auto.more_select.service.impl;

import cn.infocore.springboot.generate.auto.more_select.domain.Student;
import cn.infocore.springboot.generate.auto.more_select.mapper.StudentMapper;
import cn.infocore.springboot.generate.auto.more_select.service.StudentService;
import cn.infocore.springboot.generate.auto.more_select.vo.QuestionStudentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public List<QuestionStudentVO> queryStudentInfoById(IPage<QuestionStudentVO> page, QueryWrapper<Student> wrapper) {
        return baseMapper.queryStudentInfoById(page, wrapper);
    }
}
