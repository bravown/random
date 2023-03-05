import com.wn.RandomApplication;
import com.wn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 王宁 2021/12/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class 值传递和引用传递测试 {

    @Test
    public void 值传递() {
        User user = new User();
        赋值User(user);
        System.out.println(user);
    }

    public void 赋值User(User user) {
        user.setAge(12);
    }

    @Test
    public void 引用传递() {
        Integer a = 12;
        改变a(a);
        System.out.println(a);
    }

    public void 改变a(Integer a) {
        a = a + 1;
    }
}
