import com.rabbitmq.client.*;
import com.wn.RandomApplication;
import com.wn.util.MqConnectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class ConsumerTest {

    //设定队列名称(已存在的队列)
    private static final String QUEUE_NAME = "queue1";

    public static void main(String[] args) throws IOException, TimeoutException {


        //从MQ工具类获取连接信息
        Connection connection = MqConnectionUtils.getConnection();

        //创建一个通道
        Channel channel = connection.createChannel();

        // 声明交换机
        // @param1 exchange 交换机名称
        // @param2 type 交换机类型
        // @param3 durable 是否持久化
        // @param4 autoDelete 是否自动删除
        // @param5 internal 当前Exchange是否用于RabbitMQ内部使用，默认false
        // @param6 arguments 扩展参数，用户扩展AMQP定制化协议
        //
        // @return AMQP.Exchange.DeclareOk 用来标识成功声明一个交换机
        //         打印信息: #method<exchange.declare-ok>()
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare("fanout_exchange_wn",
                BuiltinExchangeType.FANOUT, false, false, false, null);

        //准备发送的消息内容
        String msg = "生产者发送的一条消息";
        //发送消息给队列

        /**
         * 参数1:交换机，不定义也会有默认的，因为我们的消息是通过交换机来进行投递给队列的，所以交换机不可能没有
         * 参数2:简单模式：队列名称
         * 参数3:消息的状态控制
         * 参数4:消息内容
         */
        channel.basicPublish("fanout_exchange_wn", QUEUE_NAME, null, msg.getBytes());
        System.out.print("发送成功");
        //关闭通道
        channel.close();
        connection.close();
    }

    @Test
    public void customer() {
        // 1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. 设置连接属性
        connectionFactory.setHost("150.158.40.126");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            // 3. 从连接工厂中获取连接
            connection = connectionFactory.newConnection("customer");
            // 3. 在连接中创建信道，RabbitMQ中的所有操作都是在信道中完成的
            channel = connection.createChannel();
            System.out.println("等待接收消息......");

            /*
             * RabbitMQ推送给消费者消息回调接口，在该接口中用于编写如何对消息进行处理。
             * @param1 消费者注册到RabbitMQ之后，RabbitMQ给生成的一个该消费者的唯一标识
             * @param2 推送过来的消息的信息。其中包括真正的数据body(消息体)，
             *         Properties(消息的属性信息)和其它信息。
             */
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("接收到队列发送来的消息：" + message);
            };

            /*
             * rabbitmq取消该消费者对信道中队列的订阅时，调用的回调接口。
             * 当我们在RabbitMQ管理界面手动删除该队列时，就会调用该接口。
             * @param1 消费者注册到RabbitMQ之后，RabbitMQ给生成的一个该消费者的唯一标识
             */
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("消息消费被中断" + consumerTag);
            };

            //自动应答 ,稍后介绍
            boolean autoAck = true;

            // 向队列注册自己
            String consumerTag = channel.basicConsume("simple_pattern", autoAck, deliverCallback, cancelCallback);
            System.out.println("注册到RabbitMQ中后，RabbitMQ给的唯一标识是：" + consumerTag);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("消息接收异常");
        } finally {
            // 释放关闭连接信道与连接
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
