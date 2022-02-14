import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest3 {
    @Test
    public void Test01() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", " ", "jkl");
        // string这个名字是任意取的，叫什么都行，他的意思是代指遍历集合的其中一个元素，那->后面的就是过滤条件了，只要符合条件的元素都不会被
        // 过滤掉，不符合条件的都被删了。
        // 这里有一个快速记忆方法，可以片面地把stream()理解为把集合中的元素发散开来，然后一顿处理，最后再收回来即collect()。
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
    }

    @Test
    public void Test02() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数,并去重
        // 这个map()方法看起来像是把->后的结果替换到原来的元素。
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
    }

    @Test
    public void Test03() {
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void Test04() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        // 下面这个跟上面的不同之处是下面的是输出元素，并在元素间用 - 隔开。而上面的是输出一个集合，输出结果两边还带着[]。
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(" - "));
        System.out.println("合并字符串: " + mergedString);
    }

    @Test
    /*统计*/
    public void Test05() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

}
