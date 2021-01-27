package cn.infocore.rabbitmq.workqueue;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/17 20:05
 * @Description Work 模型的生产者
 */
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("work");

        channel.basicQos(1);

        IntStream.range(0,20).forEach(i -> {
            try {
                channel.basicPublish("", "work", MessageProperties.PERSISTENT_TEXT_PLAIN, (i + " work queue").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        RabbitmqUtil.close(channel);
    }
}
