package tutorial_3_PublishSubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by wq on 2017/5/26.
 */
public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        // 创建一个非持久的、唯一的且自动删除的队列
        String quequeName = channel.queueDeclare().getQueue();
        // 为转发器指定队列，设置binding
        channel.queueBind(quequeName,EXCHANGE_NAME,"");

        System.out.println(" [*] Waiting for message. -------------");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                //打印到屏幕
                System.out.println(" [x] Received " + message);
            }
        };
        channel.basicConsume(quequeName,true,consumer);
    }
}
