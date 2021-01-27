package cn.infocore.rabbitmq.topic;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 15:00
 * @Description topic模型中的生产者
 */
public class Provider {
    protected static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("");

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routKey = "user.save.findAll";
        channel.basicPublish(EXCHANGE_NAME, routKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ("topic模型发送的消息 route key = " + routKey).getBytes());

        RabbitmqUtil.close(channel);
    }
}
