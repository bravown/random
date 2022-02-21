import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author 王宁 2021/12/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class String字符串测试 {

    @Test
    public void 替换测试() {
        String b = "abcabcabc";

        b = b.replace("b", "");
        //这个方法是替换所有的，如果是想找第一个是b的，可以如下
        System.out.println(b);
        String s = "abcabcabc";

        int i = s.indexOf("b");

        s = s.substring(0, i) + s.substring(i + 1);

        System.out.println(s);

        System.out.println(UUID.randomUUID().toString().substring(0, 7));
    }

    @Test
    public void 分割测试() {
        String a = "aaaaaaaab";
        String[] as = a.split("a");
        int length = as.length;
        System.out.println(length);
        Arrays.asList(as).stream().forEach(x -> System.out.println(x));
        String s = "        b";
    }

    @Test
    public void 数组api测试() {
        int[] a = {3, 5, 9, 7, 2, 2};
        Arrays.sort(a); // 排序
        // 打印数组
        for (int item : a)
            System.out.print(item + " ");
        System.out.println();
        // 二分查找
        int ind1 = Arrays.binarySearch(a, 2);
        int ind2 = Arrays.binarySearch(a, 4);
        int ind3 = Arrays.binarySearch(a, 2, 4, 5);
        System.out.println("2的查找位置：" + ind1);
        System.out.println("4的查找位置：" + ind2);
        System.out.println("5的查找位置：" + ind3);
        System.out.println("数组a的长度" + a.length);

        String[] strArray = {"aa", "bc", "ab", "cd"};
        Arrays.sort(strArray);
        int ind4 = Arrays.binarySearch(strArray, "bc");
        System.out.println("'bc'的查找位置是：" + ind4);
    }

}
