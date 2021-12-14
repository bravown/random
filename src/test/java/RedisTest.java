import com.wn.RandomApplication;
import com.wn.entity.User;
import com.wn.mapper.UserMapper;
import com.wn.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类测试
 *
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class RedisTest {

    private static final User user = new User(1L, "李", 10);
    private static final String a[] = {"1", "2", "3", "4", null, "", " ", "一"};
    private static final String s1 = "123";
    private static final String s2 = "abc";
    private static final String s3 = "一二三";
    private static final String s4 = "";
    private static final String s5 = " ";
    private static final String s6 = null;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    /*测试key*/
    @Test
    public void Test24() throws ParseException {

        redisTemplate.opsForValue().set("name:one","me");
        redisTemplate.opsForValue().set("name:2","me2");
        redisTemplate.opsForValue().set("","me2");
        redisTemplate.opsForValue().set("name:","me2");

        System.out.println(redisTemplate.type("kong2"));
        System.out.println(redisTemplate.opsForValue().get("name:"));
    }

    @Test
    public void Test25() {

    }

    @Test
    public void Test26() {

    }

    @Test
    public void Test27() {

    }

    @Test
    public void Test28() {

    }

    @Test
    public void Test29() {

    }

    @Test
    public void Test30() {

    }

    @Test
    public void Test31() {

    }

    @Test
    public void Test32() {

    }

    @Test
    public void Test33() {

    }

    @Test
    public void Test34() {

    }

    @Test
    public void Test35() {

    }

    @Test
    public void Test36() {

    }

    @Test
    public void Test37() {
    }


}