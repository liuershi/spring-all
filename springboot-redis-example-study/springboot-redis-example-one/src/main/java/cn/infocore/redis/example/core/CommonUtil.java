package cn.infocore.redis.example.core;

import org.springframework.util.DigestUtils;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/19 11:09
 * @Description 通用工具类
 */
public class CommonUtil {

    public static String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
