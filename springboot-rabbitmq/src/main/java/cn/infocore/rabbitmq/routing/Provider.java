package cn.infocore.rabbitmq.routing;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 13:48
 * @Description Routing 模型中的生产者
 */
public class Provider {
    protected static final String EXCHANGE_NAME = "logs_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("");

        // 声明交换机和交换机类型，当前类型是直连
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        // 通道会绑定的具体路由key
        String routeKey = "error";
        // 为通道绑定具体的队列和交换机以及route key
        channel.queueBind(queueName, EXCHANGE_NAME, routeKey);
        // 发布消息
        channel.basicPublish(EXCHANGE_NAME, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ("以direct模式发送的消息 route key = " + routeKey).getBytes());

        RabbitmqUtil.close(channel);
    }
}
