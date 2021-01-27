package cn.infocore.rabbitmq.springboot.workqueues;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 19:42
 * @Description
 */
@Component("work")
public class Consumer {

    /**
     * 消费者1
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("消费者1消费的消息：" + message);
    }

    /**
     * 消费者2
     */
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("消费者2消费的消息：" + message);
    }
}
