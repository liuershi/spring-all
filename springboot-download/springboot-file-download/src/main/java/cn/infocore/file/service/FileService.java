package cn.infocore.file.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 15:21
 * @Description
 */
public interface FileService {
    /**
     * 下载文件接口
     * @param response
     * @param file 文件名
     */
    void download(HttpServletResponse response, String file);
}
