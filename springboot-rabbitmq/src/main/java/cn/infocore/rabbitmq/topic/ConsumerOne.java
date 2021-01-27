package cn.infocore.rabbitmq.topic;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 15:08
 * @Description
 */
public class ConsumerOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("");

        channel.exchangeDeclare(Provider.EXCHANGE_NAME, "topic");

        String queueName = channel.queueDeclare().getQueue();
        // *：表示后面匹配一个字符串，例如user.save，但是不匹配这种user.save.findAll，若要匹配则要设置为user.*.*
        channel.queueBind(queueName, Provider.EXCHANGE_NAME, "user.*.*");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer one receive message is : " + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
