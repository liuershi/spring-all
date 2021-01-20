package cn.infocore.file.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 15:18
 * @Description
 */
@Slf4j
public class FileUtil {

    public static void downloadFile(HttpServletResponse response, String fileName){
        String path;
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[1024];
        BufferedInputStream inputStream = null;
        OutputStream os = null;

        try {
            // 获取files文件夹路径
            path = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "files/" + fileName).getPath();
            log.info("class path :{} ", path);
            os = response.getOutputStream();
            inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            int readLength = inputStream.read(buffer);
            while (readLength != -1) {
                os.write(buffer, 0, readLength);
                os.flush();
                readLength = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
