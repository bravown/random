import com.wn.RandomApplication;
import com.wn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class Optional类测试 {

    @Test
    public void Test01() {
        User user = new User();

        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException());
    }
}
