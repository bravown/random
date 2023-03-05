
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;

/**
 * 有关容器方法的测试
 *
 * @author 王宁 2021/11/18
 */
public class 容器测试 {

    public static void main(String[] args) {
        List<String> list = null;
        Optional.ofNullable(list).orElse(Lists.newArrayList()).forEach(System.out::println);
    }
}
