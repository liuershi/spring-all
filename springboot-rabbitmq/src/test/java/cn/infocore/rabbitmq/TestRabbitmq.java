package cn.infocore.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/16 16:57
 * @Description rabbitmq 测试类
 */
public class TestRabbitmq {

    @Test
    public void helloWorld() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("rabbitmq");

        // 设置rabbitmq地址
        factory.setHost(resourceBundle.getString("rabbitmq.host"));
        // 设置端口
        factory.setPort(Integer.parseInt(resourceBundle.getString("rabbitmq.port")));
        // 设置虚拟主机
        factory.setVirtualHost("/ems");
        // 设置访问的用户名和密码
        factory.setUsername("adminzw");
        factory.setPassword("admin123");

        // 创建连接
        Connection connection = factory.newConnection();
        // 创建队列(queue)对应的通道(channel)
        Channel channel = connection.createChannel();

        // 参数1：队列名称，既可以在web管理界面创建，也可以通过代码生成，即不存在时就创建
        // 参数2：是否持久化
        // 参数3：是否是独占模式，true即独占，此时不允许其他channel使用，false则允许共享
        // 参数4：是否自动删除，在队列中消息消耗完时是否自动删除队列
        // 参数5：额外参数
        channel.queueDeclare("hello", false, false,false, null);

        // 发布消息，直接发送到队列，此时不用设置交换机
        channel.basicPublish("", "hello", null, "hello world".getBytes());
        // 关闭连接
        channel.close();
        connection.close();
    }
}
