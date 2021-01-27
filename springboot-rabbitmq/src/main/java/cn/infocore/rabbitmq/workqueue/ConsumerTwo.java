package cn.infocore.rabbitmq.workqueue;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/17 20:13
 * @Description
 */
public class ConsumerTwo {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("work");

        channel.basicQos(1);

        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2- receive message:" + new String(body));

                // 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
