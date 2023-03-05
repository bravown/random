import com.wn.RandomApplication;
import com.wn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author 王宁 2022/3/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class Optional测试 {

    @Test
    public void optionalTest() throws Exception {
        User user = new User();
        user = null;
        User user1 = Optional.ofNullable(user).orElse(new User());
        System.out.println(user1);
    }

    @Test
    public void Test02() {
        User user = new User();
        user.setAdress("Developer");
        Optional<User> user1 = Optional.ofNullable(user)
                .filter(u -> u.getAdress() == "Developerr");
        String position = (String) user1.get().getAdress();
        System.out.println(position);

    }
}
