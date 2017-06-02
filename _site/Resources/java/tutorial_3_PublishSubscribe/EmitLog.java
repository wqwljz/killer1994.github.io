package tutorial_3_PublishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by wq on 2017/5/26.
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        for (int i =0;i<50;i++) {
            String message = "this is a message" + i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println(" [x] Sent " + message);
        }

        channel.close();
        connection.close();
    }
}
