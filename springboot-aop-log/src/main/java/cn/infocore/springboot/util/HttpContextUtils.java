package cn.infocore.springboot.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 19:51
 * @Description 获取HttpServletRequest 工具类
 */
public class HttpContextUtils {
    private HttpContextUtils(){}

    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
