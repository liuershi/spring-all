package cn.infocore.rabbitmq.springboot.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 19:55
 * @Description
 */
@Component(value = "fanout")
public class Consumer {

    /**
     * 消费者1
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,// 绑定队列，不指定队列名时使用临时队列名称，自动获取
                    exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机，指定交换机类型
            )
    })
    public void receive1(String message){
        System.out.println("Publish/Subscribe模型消费者1消费的消息：" + message);
    }

    /**
     * 消费者2
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void receive2(String message){
        System.out.println("Publish/Subscribe模型消费者2消费的消息：" + message);
    }
}
