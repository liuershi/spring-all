package cn.infocore.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/16 17:37
 * @Description Rabbitmq 的工具类
 */
public class RabbitmqUtil {

    private static ConnectionFactory factory;

    /**
     * 类加载时直接创建连接工厂
     */
    static {
        factory = new ConnectionFactory();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("rabbitmq");
        // 设置rabbitmq地址
        factory.setHost(resourceBundle.getString("rabbitmq.host"));
        // 设置端口
        factory.setPort(Integer.parseInt(resourceBundle.getString("rabbitmq.port")));
        // 设置虚拟主机
        factory.setVirtualHost(resourceBundle.getString("rabbitmq.virtual"));
        // 设置访问的用户名和密码
        factory.setUsername(resourceBundle.getString("rabbitmq.username"));
        factory.setPassword(resourceBundle.getString("rabbitmq.password"));
    }

    /**
     * 获取队列对应的通道
     * @param queue 创建或获取的队列名称
     * @return queue对应的通道
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel getChannel(String queue) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = factory.newConnection();
        // 创建队列(queue)对应的通道(channel)
        Channel channel = connection.createChannel();

        // 参数1：队列名称，既可以在web管理界面创建，也可以通过代码生成，即不存在时就创建
        // 参数2：是否持久化，不持久化则rabbitmq服务重启时队列会丢失，该参数只持久化队列并不持久化队列中消息
        // 参数3：是否是独占模式，true即独占，此时不允许其他channel使用，false则允许共享
        // 参数4：是否自动删除，在队列中消息消耗完时是否自动删除队列，注意，就算消息被消费完，只有当消费者断开与队列的连接时才会去删除队列
        // 参数5：额外参数
        if (!StringUtils.isEmpty(queue)){
            channel.queueDeclare(queue, true, false,true, null);
        }

        return channel;
    }

    /**
     * 关闭连接
     * @param channel
     */
    public static void close(Channel channel){
        try {
            if (!Objects.isNull(channel)) {
                channel.close();

                if (!Objects.isNull(channel.getConnection())) {
                    channel.getConnection().close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
