package tutorial_6_RPC;

import com.rabbitmq.client.*;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wq on 2017/5/26.
 */
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int i){
        if (i == 0) return 0;
        if (i == 1) return 1;
        return fib(i-1) + fib(i-2);
    }

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME,false,false,false,null);

            channel.basicQos(1);
            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";
                    try {
                        String message = new String(body,"UTF-8");
                        int n = Integer.parseInt(message);

                        System.out.println(" [.] fib(" + message + ")");
                        response += fib(n);
                    } catch (RuntimeException e) {
                        System.out.println(" [.] "+e.toString());
                    } finally {
                        channel.basicPublish("", properties.getReplyTo(),replyProps,response.getBytes("UTF-8"));

                        channel.basicAck(envelope.getDeliveryTag(),false);
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            // loop to prevent reaching finally block
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException _ignore) {}
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (IOException e) {}

            }
        }
    }
}
