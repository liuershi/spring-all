package cn.infocore.rabbitmq.springboot.topics;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 20:16
 * @Description
 */
@Component(value = "topics")
public class Consumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic-exchange", type = "topic"),
                    key = {"user.*"}
            )
    })
    public void receive1(String message){
        System.out.println("topics模型中消费者1消费的消息：" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic-exchange", type = "topic"),
                    key = {"user.#","order.#"}
            )
    })
    public void receive2(String message){
        System.out.println("topics模型中消费者2消费的消息：" + message);
    }
}
