import com.wn.RandomApplication;
import com.wn.entity.Team;
import com.wn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stream()方法的测试，也可当作笔记
 * 测试的方法：
 * 1、将一个对象的某一个成员变量取出来，这个成员变量可能是变量，也可能是对象，然后取里面的值当作key或value，转成list或map
 *
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class stream流测试 {


    static User initUser = new User(1L, "张三", 23,
            new Team(1L, "Haier", "青岛市",
                    new ArrayList<String>() {{
                        add("山东省");
                        add("青岛市");
                        add("崂山区");
                    }}, new HashMap<String, String>() {{
                put("boos1", "张三");
                put("boos2", "李四");
            }}, null));
    static Team initTeam = new Team(1L, "Haier", "青岛市", null, null, new ArrayList<User>() {{
        add(initUser);
    }});

    @Test
    /**
     * 列举以下测试用例:
     * 1. 成员变量是对象,对象有一个list成员变量,把这个list转为set
     * 2. 成员变量是对象,对象有一个list成员变量,且里面放的是实体类,把这个list转为流,并把这个里面实体类对象的某两个属性拿出来,作为key或value
     * 3.
     */
    public void 第1个() {
        System.out.println(initUser);
        // 1
        Set<String> collect = initUser.getTeam().getRegion().stream().collect(Collectors.toSet());
        System.out.println(collect);
        // 2 当key有冲突的时候,选择的value是之前的key的.
        // 而且前两个参数必须是映射函数,也就是::,第三个是解决冲突的合并函数
        Map<Long, String> collect1 = initTeam.getStaff().stream().collect(Collectors.toMap(User::getId, User::getName, (k1, k2) -> k1));
        System.out.println(collect1);


    }

    @Test
    public void map(){
        HashMap map = new HashMap<String, String>();
        map.put("1","1");
        map.put("2","1");

    }

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
    public void 你好() {
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
