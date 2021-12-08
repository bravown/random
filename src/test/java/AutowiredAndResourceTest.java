import com.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author 王宁 2021/12/8
 */
public class AutowiredAndResourceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserService userService2;
    @Resource
    UserService userService3;

}
