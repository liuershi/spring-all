package cn.infocore.rabbitmq.springboot;

import cn.infocore.rabbitmq.RabbitmqApplicationMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/26 19:16
 * @Description
 */
@SpringBootTest(classes = RabbitmqApplicationMain.class)
@RunWith(SpringRunner.class)
public class ProviderTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloworld(){
        System.out.println("开始生产消息");
        rabbitTemplate.convertAndSend("helloWorld", "整合springboot的helloworld模型的消息");
    }

    @Test
    public void testWorkqueues(){
        System.out.println("开始生产消息");
        IntStream.range(0, 10).forEach(i -> rabbitTemplate.convertAndSend("work", "整合springboot的work queue模型的消息 ： " + i));
    }

    @Test
    public void testFanout(){
        System.out.println("开始生产消息");
        IntStream.range(0, 10).forEach(i -> rabbitTemplate.convertAndSend("logs", "", "整合springboot的Publish/Subscribe模型的消息 ： " + i));
    }

    @Test
    public void testRouting(){
        System.out.println("开始生产消息");
        rabbitTemplate.convertAndSend("logs-system", "debug", "整合springboot的routing模型的消息");
    }

    @Test
    public void testTopics(){
        System.out.println("开始生产消息");
        rabbitTemplate.convertAndSend("topic-exchange", "order", "整合springboot的routing模型的消息 order");
    }
}
