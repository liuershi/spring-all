package cn.infocore.jedis.demo01;

import cn.infocore.jedis.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/14 14:42
 * @Description 使用Jedis第一个应用
 */
public class Test01 {
    public static final String HOST = "192.168.13.165";
    public static final int PORT = 6379;

    public static void main(String[] args) {
        MyThread ordinary = new MyThread("普通会员", 10);
        ordinary.start();

        MyThread platinum = new MyThread("铂金会员", 30);
        platinum.start();

        MyThread diamond = new MyThread("钻石会员", Integer.MAX_VALUE);
        diamond.start();
    }
}

class CustomizeService {

    public void service(String id, int num) {
//        Jedis jedis = new Jedis(Test01.HOST, Test01.PORT);
        Jedis jedis = JedisUtil.getJedis();
        String user = jedis.get("user:" + id);
        try {
            if (user == null) {
                jedis.setex("user:" + id, 60*10, String.valueOf(Long.MAX_VALUE - num));
            } else {
                jedis.incr("user:"+id);
                shopping(id, num - (Long.MAX_VALUE - Long.parseLong(jedis.get("user:"+id))));
            }
        } catch (JedisDataException e) {
            if ("普通会员".equals(id)) {
                System.out.println("购物次数已达日上限，请升级为黄金会员");
            } else {
                System.out.println("购物次数已达日上限，请升级为钻石会员");
            }
        } finally {
            jedis.close();
        }
    }

    protected void shopping(String id, Long number){
        System.out.println("用户" + id + "正在购物...当前是第" + number +"次购物");
    }
}

class MyThread extends Thread {

    CustomizeService service = new CustomizeService();

    public MyThread(String name, int num) {
        this.name = name;
        this.num = num;
    }

    /**
     * 用户等级
     */
    private String name;

    /**
     * 用户等级对应次数
     */
    private int num;

    @Override
    public void run() {
        while (true) {
            int randomTime = new Random().nextInt(1000);
            try {
                TimeUnit.MILLISECONDS.sleep(randomTime);
                service.service(name, num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
