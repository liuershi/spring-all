package cn.infocore.springboot.generate.auto.more_select.controller;


import cn.infocore.springboot.generate.auto.more_select.domain.Student;
import cn.infocore.springboot.generate.auto.more_select.service.StudentService;
import cn.infocore.springboot.generate.auto.more_select.vo.QuestionStudentVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wei.zhang@infocore.cn
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/more_select/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/list/union")
    public List<QuestionStudentVO> listStudentQuestionInfo(Page<QuestionStudentVO> page, long id){
        return studentService.queryStudentInfoById(page, new QueryWrapper<Student>().eq("s.id", id));
    }
}

