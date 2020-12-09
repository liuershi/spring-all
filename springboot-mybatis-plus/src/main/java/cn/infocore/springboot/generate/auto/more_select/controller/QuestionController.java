package cn.infocore.springboot.generate.auto.more_select.controller;


import cn.infocore.springboot.generate.auto.more_select.domain.Question;
import cn.infocore.springboot.generate.auto.more_select.service.QuestionService;
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
@RequestMapping("/more_select/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping(value = "list/all")
    public List<Question> listAllQuestion(){
        return service.list();
    }
}

