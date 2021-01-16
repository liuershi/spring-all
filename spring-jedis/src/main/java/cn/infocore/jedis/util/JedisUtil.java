package cn.infocore.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/14 18:48
 * @Description Jedis 工具类
 */
public class JedisUtil {
    public static String HOST;
    public static int PORT;
    public static int MAX_CONNECTION;
    public static int MAX_IDLE_CONNECTION;

    public static final JedisPool JEDIS_POOL;

    /**
     * Jedis连接池，类加载时初始化
     */
    static {
        // 获取配置文件 redis.properties 中的信息，不需要写.properties后缀
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        HOST = resourceBundle.getString("redis.host");
        PORT = Integer.parseInt(resourceBundle.getString("redis.port"));
        MAX_CONNECTION = Integer.parseInt(resourceBundle.getString("redis.max.connection"));
        MAX_IDLE_CONNECTION = Integer.parseInt(resourceBundle.getString("redis.max.idle.connection"));

        JedisPoolConfig jpc = new JedisPoolConfig();
        // 最大连接数
        jpc.setMaxTotal(MAX_CONNECTION);
        // 最大空闲连接数
        jpc.setMaxIdle(MAX_IDLE_CONNECTION);

        JEDIS_POOL =  new JedisPool(jpc, HOST, PORT);
    }

    /**
     * 获取Jedis
     * @return
     */
    public static Jedis getJedis(){
        return JEDIS_POOL.getResource();
    }
}
