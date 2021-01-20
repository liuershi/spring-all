package cn.infocore.file.controller;

import cn.infocore.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 15:20
 * @Description
 */
@RestController
@Slf4j
public class FileController {

    @Autowired
    private FileService service;

    @GetMapping(value = "/file/download/{file}")
    public void fileDownload(HttpServletResponse response,@PathVariable("file") String file){
        log.info("file name is {}", file);
        service.download(response,file);
    }
}
