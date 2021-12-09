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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest2 {

    private static final User user = new User(1L, "李", 10);
    private static final String[] a = {"1", "2", "3", "4", null, "", " ", "一"};
    private static final String[] b = {"1", "2", "3", "4", "", " ", "一"};
    private static final String[] c = {"1", "2", "3", "4", " ", "一"};
    private static final String s1 = "123";
    private static final String s2 = "abc";
    private static final String s3 = "一二三";
    private static final String s4 = "";
    private static final String s5 = " ";
    private static final String s6 = null;
    private static final String set1 = "set1";
    private static final String set2 = "set2";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void Test0() {
        Set intersect = redisTemplate.opsForSet().intersect(set1, set2);
        System.out.println(intersect);
    }

    @Test
    public void Test1() {
        System.out.println(redisTemplate.opsForSet().union(set1, set2));
    }

    @Test
    public void Test2() {
        Set members = redisTemplate.opsForSet().members(set1);
        System.out.println(members);
        System.out.println(redisTemplate.opsForSet().randomMembers(set1, members.size()));
        System.out.println(redisTemplate.opsForSet().distinctRandomMembers(set1, members.size()));

    }

    @Test
    public void Test3() {
        String currentTimeString = String.valueOf(System.currentTimeMillis());
        System.out.println(currentTimeString);
        Long currentTimeLong = Long.parseLong(currentTimeString);
        System.out.println(currentTimeLong);

        Date date = new Date(currentTimeLong);
        System.out.println(date);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(date));
    }

    @Test
    public void Test4() {
        System.out.println("k");
        System.out.println("k");
    }

    @Test
    public void Test5() {

    }

    @Test
    public void Test6() {

    }

    @Test
    public void Test7() {

    }

    @Test
    public void Test8() {

    }

    @Test
    public void Test9() {

    }

    @Test
    public void Test10() {

    }
}