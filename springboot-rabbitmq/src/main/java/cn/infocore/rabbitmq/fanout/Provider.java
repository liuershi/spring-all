package cn.infocore.rabbitmq.fanout;

import cn.infocore.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 10:13
 * @Description Publish/Subscribe 模型的生产者
 */
public class Provider {
    protected static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel("");

        // 将通道声明指定的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 在该模型下，队列的名称没有意义，直接使用空字符串即可
//        channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, "I am java".getBytes());

        // 发布消息
        IntStream.range(0, 10).forEach(i -> {
            try {
                channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN,
                        ("message" + i).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //释放资源
        RabbitmqUtil.close(channel);
    }
}
