package cn.infocore.rabbitmq.helloworld;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/16 17:36
 * @Description Hello World 模型中的生产者
 */
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("hello");

        // 参数1：交换机名称 参数2：队列名称 参数3：额外参数 参数4：发送的消息
        // MessageProperties.PERSISTENT_TEXT_PLAIN:表示队列中的消息需要持久化，即服务重启消息也不会丢失
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello world zw".getBytes());

        RabbitmqUtil.close(channel);
    }
}
