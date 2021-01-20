package cn.infocore.file.service.impl;

import cn.infocore.file.service.FileService;
import cn.infocore.file.util.FileUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 15:21
 * @Description
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void download(HttpServletResponse response, String file) {
        FileUtil.downloadFile(response, file);
    }
}
