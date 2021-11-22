import com.wn.RandomApplication;
import com.wn.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void Test1(){
        Map<Integer,String> map = new HashMap<>();
        Integer count = new Integer(1);
        map.put(count,"wang");
        map.put(count,"ol");
    }

    @Test
    public void Test2(){
        System.out.println(userService.queryUser());
    }
}
