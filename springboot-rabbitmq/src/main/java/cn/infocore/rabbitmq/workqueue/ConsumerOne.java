package cn.infocore.rabbitmq.workqueue;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/17 20:13
 * @Description
 */
public class ConsumerOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("work");

        // 设置一次只能消费一个
        channel.basicQos(1);

        // 参数2：是否自动确认，注意，如果自动确认，那么消费者拿到队列中的消息时消息队列就会删除其中的消息，
        // 不管消费者是否真正处理完毕，如果此时消费者宕机，那么未处理完的消息就会丢失，官方文档中建议我们手动确认
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1- receive message:" + new String(body));
                // 参数1：确认具体是哪个消息 参数2：是否允许同时确认多个消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
