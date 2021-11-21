import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void MyTest(){
        Map<Integer,String> map = new HashMap<>();
        Integer count = new Integer(1);
        map.put(count,"wang");
        map.put(count,"ol");
    }
}
