package cn.infocore.excel.controller;

import cn.infocore.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:48
 * @Description
 */
@RestController
public class ExcelController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/excel/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        service.exportExcel(response);
    }
}
