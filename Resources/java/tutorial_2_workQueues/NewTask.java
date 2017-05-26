package tutorial_2_workQueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * Created by wq on 2017/5/25.
 */
public class NewTask {
    private final static String TASK_QUEUE_NAME  = "task_queue";

    public static void main(String[] args) throws IOException {
        //
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // 队列持久化
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME , durable, false, false, null);

        //String message = getMessage(args);
        for (int i=0;i<100;i++) {
            String message = "hello rabbitmq . " + i;

            // 通过设置MessageProperties（implements BasicProperties）值为PERSISTENT_TEXT_PLAIN。
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();

    }

    private static String getMessage(String[] strings) {
        if(strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    };

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if(length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 0 ; i < length ; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
