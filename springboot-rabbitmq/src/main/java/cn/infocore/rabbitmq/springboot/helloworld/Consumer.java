package cn.infocore.rabbitmq.springboot.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 19:24
 * @Description
 */
// 需要被容器扫描，避免在容器中冲突指定一个名称
@Component(value = "helloword")
@RabbitListener(queuesToDeclare = @Queue(value = "helloWorld"))
public class Consumer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("receive message is : " + message);
    }
}
