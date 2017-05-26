package tutorial_2_workQueues;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by wq on 2017/5/25.
 */
public class Worker {
    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 预取个数
        int prefectCount = 1;
        channel.basicQos(prefectCount);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    // 应答回复
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 设置为 ack 启动，即 打开应答机制
        boolean noAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, noAck, consumer);
    }

    private static void doWork(String task){
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
