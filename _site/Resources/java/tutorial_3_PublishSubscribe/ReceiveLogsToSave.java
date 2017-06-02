package tutorial_3_PublishSubscribe;

import com.rabbitmq.client.*;
import rabbitmq.QueueConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wq on 2017/5/26.
 */
public class ReceiveLogsToSave {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, InterruptedException {
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

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(quequeName,true,consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            print2File(message);
        }

    }

    private static void print2File(String msg) {
        try{
            String dir = ReceiveLogsToSave.class.getClassLoader().getResource("").getPath();
            String logFileName = new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date());
            File file = new File(dir, logFileName+".txt");
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write((msg + "\r\n").getBytes());
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ok?");
        }
    }
}
