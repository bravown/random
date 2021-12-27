import com.wn.ConsumerAppliaction;
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
@SpringBootTest(classes = ConsumerAppliaction.class)
public class ConsumerTest {

    // 使用RabbitTemplate，这提供了接收、发送等等方法
    @Autowired
    RabbitTemplate rabbitTemplate;


}
