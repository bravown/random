import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 王宁 2021/12/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class RabbitMQTest {

    // 使用RabbitTemplate，这提供了接收、发送等等方法
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void Test01(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageId2 = String.valueOf(UUID.randomUUID());
        System.out.println(messageId);
        System.out.println(messageId2);
    }

    @Test
    public void sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "这是生产者发的一条消息。";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("OliverDirectExchange", "TestDirectRouting", map);
    }

}
