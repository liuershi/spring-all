package cn.infocore.rabbitmq.springboot.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 20:08
 * @Description
 */
@Component(value = "routing")
public class Consumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs-system"),
                    key = {"debug", "info", "warn"}
            )
    })
    public void receive1(String message){
        System.out.println("routing模型中消费者1消费消息：" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs-system"),
                    key = {"debug"}
            )
    })
    public void receive2(String message){
        System.out.println("routing模型中消费者2消费消息：" + message);
    }
}
