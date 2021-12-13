import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wn.RandomApplication;
import com.wn.entity.User;
import com.wn.mapper.UserMapper;
import com.wn.utils.RedisUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest {

    private static final User user = new User(1L,"李",10);
    private static final String a[] = {"1", "2", "3", "4", null, "", " ", "一"};
    private static final String s1 = "123";
    private static final String s2 = "abc";
    private static final String s3 = "一二三";
    private static final String s4 = "";
    private static final String s5 = " ";
    private static final String s6 = null;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Before
    public void init() {
        User user = new User();
        user.setName("李");
        user.setAge(10);
        String a[] = {"1", "2", "3", "4", null, "", " ", "一"};
        String s1 = "123";
        String s2 = "abc";
        String s3 = "一二三";
        String s4 = "";
        String s5 = " ";
        String s6 = null;
    }

    @Test
    public void Test1() {
        Map<Integer, String> map = new HashMap<>();
        Integer count = new Integer(1);
        map.put(count, "wang");
        map.put(count, "ol");
    }

    @Test
    public void Test2() {
        String s1 = "123";
        String s2 = "123a";
        String s3 = "";
        String s4 = " ";
        String s5 = "123 ";
        String s6 = " 123";
        String s7 = "1 23";
        String s8 = "2021-11-30 12:88:21";
        String s9 = s8.substring(0, 4);
        String s10 = s8.substring(0, 4) + s8.substring(5, 7) + s8.substring(8, 10);

        boolean matches1 = s1.matches("[0-9]+");
        boolean matches2 = s2.matches("[0-9]+");
        boolean matches3 = s3.matches("[0-9]+");
        boolean matches4 = s4.matches("[0-9]+");
        boolean matches5 = s5.matches("[0-9]+");
        boolean matches6 = s6.matches("[0-9]+");
        boolean matches7 = s7.matches("[0-9]+");
        System.out.println(matches1 + ":" + matches2 + matches3 + ":" + matches4 + matches5 + ":" + matches6 + matches7);
        System.out.println(s10);

    }

    @Test
    public void Test3() throws Exception {
        Connection connect = Jsoup.connect("https://movie.douban.com/subject/35073565/comments?start=0&limit=30");
        Document doc =
                connect.post();
//        System.out.println(doc);
        Elements element = doc.select("#wrapper .comment-item .short");
//        System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓"+element);

//        System.out.println(element.select("SPAN").text());

        for (Element element1 : element) {
            String href = element1.text();
            System.out.println("输出的是:" + href);
        }


        CollectionUtils.isNotEmpty(element);
        Collections.emptyEnumeration();
    }

    @Test
    public void Test4() {
        Map<Integer, String> map = new HashMap<>();
        boolean notEmpty = MapUtils.isNotEmpty(map);
        map.put(1, "我");
        boolean notEmpty1 = MapUtils.isNotEmpty(map);
        System.out.println(notEmpty + "" + notEmpty1);
    }

    @Test
    public void Test5() {
        String s1 = "123";
        String s2 = "123 ";
        String s3 = " ";
        String s4 = "";
        boolean empty1 = StringUtils.isNotBlank(s1);
        boolean empty2 = StringUtils.isNotBlank(s2);
        boolean empty3 = StringUtils.isNotBlank(s3);
        boolean empty4 = StringUtils.isNotBlank(s4);
        System.out.println(empty1);
        System.out.println(empty2);
        System.out.println(empty3);
        System.out.println(empty4);
    }

    @Test
    public void Test6() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        System.out.println(map);
        map.put(2, null);
        System.out.println(map);
        map.put(2, "我");
        System.out.println(map);
        map.put(2, null);
        System.out.println(map);
        map.put(null, null);
        System.out.println(map);
        map.put(null, "你");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(null));
        System.out.println(map);
    }

    @Test
    public void Test7() {
        Set<Integer> set = new HashSet<>();
        System.out.println(set.size() == 0);
        set.add(1);
        set.add(1);
        set.add(null);
        set.add(null);
        System.out.println(set.size() == 4);
        System.out.println(set.size());
        System.out.println(set);
    }

    @Test
    public void Test8() {
        String s1 = "123";
        String s2 = "123a\\\\\\\\";
        String s3 = "";
        String s4 = " ";
        String s5 = " 123 ";
        String s6 = " 12 3 ";
        String s7 = "1 2      3";
        s1 = s1.replace(" ", "");
        s2 = s2.replace("\\", "");
        s3 = s3.replace(" ", "");
        s4 = s4.replace(" ", "");
        s5 = s5.replace(" ", "");
        s6 = s6.replace(" ", "");
        s7 = s7.replace(" ", "");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s7);
    }

    @Test
    public void Test9() {
        String s1 = "0123456789";
        String s2 = "123456789";
        String substring = s1.substring(0, 1);
        String substring2 = s1.substring(0, 3);
        String substring3 = s1.substring(2, 5);
        String substring4 = s2.substring(0, 1);
        String substring5 = s2.substring(0, 3);
        String substring6 = s2.substring(2, 5);
        System.out.println(substring);// 0
        System.out.println(substring2);// 012
        System.out.println(substring3);// 234
        System.out.println(substring4);// 1
        System.out.println(substring5);// 123
        System.out.println(substring6);// 345
    }

    @Test
    public void Test10() {

        String s3 = "";
        String s4 = " ";
        s3 = s3.replace(" ", "");
        s4 = s4.replace(" ", "");
        System.out.println(s3);
        System.out.println(s4);
    }

    // 去掉前后空格测试
    @Test
    public void Test11() {

        String s1 = " hello ";
        String s2 = " 123a ";
        String s3 = "";
        String s4 = " ";
        String s5 = " 123 ";
        String s6 = " 12 3 ";
        String s7 = "1 2      3";
        System.out.println(s1.length());
        System.out.println(s2.length());
        System.out.println(s3.length());
        System.out.println(s4.length());
        System.out.println(s5.length());
        System.out.println(s6.length());
        System.out.println(s7.length());
        s1 = s1.trim();
        s2 = s2.trim();
        s3 = s3.trim();
        s4 = s4.trim();
        s5 = s5.trim();
        s6 = s6.trim();
        s7 = s7.trim();
        System.out.println("去掉后" + s1.length());
        System.out.println(s2.length());
        System.out.println(s3.length());
        System.out.println(s4.length());
        System.out.println(s5.length());
        System.out.println(s6.length());
        System.out.println(s7.length());
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s7);
    }

    @Test
    public void Test12() {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        String a = new String("12");
        String b = new String("12");
        System.out.print(i == j); //false
        System.out.print(a == b); //false
    }

    /*时间工具类测试*/
    @Test
    public void Test13() throws ParseException {
        String current = String.valueOf(System.currentTimeMillis());
        System.out.println(current);


        // 小写的hh取得12小时，大写的HH取的是24小时
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        System.out.println(df.format(date));

        String start = "2019-06-27 14:12:40";
        String end = "2019-08-27 14:12:40";
        Date date1 = df.parse(start);
        Date date2 = df.parse(end);
        System.out.println(date2.getTime() - date1.getTime());

    }

    @Test
    public void Test14() throws Exception {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);

        String str = "2021-12-06 00:00:00";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = format.parse(str);
        System.out.println(date);

        String current = String.valueOf(System.currentTimeMillis());
        System.out.println(current);

        Date date1 = new Date(current);
        System.out.println("date" + date1);
    }

    @Test
    public void Test15() throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date = sf.parse("2021-12-06 12:12:12");
        System.out.println(date.getTime());
        Date d = new Date(1638763932000L);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sf.format(d);
        String s = JSON.toJSONString(format);
        System.out.println(s);
    }

    /*Json测试*/
    @Test
    public void Test16() {
        User user = new User();
        user.setName("程");
        user.setAge(19);
        String s = JSON.toJSONString(user);
        System.out.println(s);

        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
        Object name = jsonObject.getString("name");
        System.out.println(name);
        User user1 = JSON.parseObject(s, User.class);
        System.out.println(user1);
    }

    /*正则表达式*/
    @Test
    public void Test17() {
        String s = "17176096614";

        String regex = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";//定义手机好规则
        boolean flag = s.matches(regex);//判断功能
        System.out.println("flag:" + flag);

        String word = "〇";
        String regex2 = "^[\\u4e00-\\u9fa5]{0,}$";
        boolean flag2 = word.matches(regex2);//判断功能
        System.out.println(flag2);
    }

    @Test
    public void Test18() {
        String a = "hello";

        String b = "hello";

        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> hashMap2 = new HashMap<>();
    }

    @Test
    public void Test19() {
        User user = new User();
        user.setName("程");
        user.setAge(19);
        User user2 = new User();
        user2.setName("易");
        user2.setAge(11);
        List<User> list = new ArrayList<>();
        list.add(user2);
        list.add(user);
        redisTemplate.opsForList().leftPush("user:administrator:list", list);

        String[] books = new String[]{"java编程思想", "springboot从入门到精通"};
        redisTemplate.opsForList().leftPushAll("book:list", books);
        //从左边插入一个集合
        List<String> list2 = new ArrayList<String>();
        list2.add("鬼泣5");
        list2.add("荒野大镖客2");
        list2.add("仙剑奇侠传7");
        redisTemplate.opsForList().leftPushAll("game:list", list2);
        redisUtil.expire("user:administrator:list", 10);
        System.out.println(redisUtil.get("a:b:c:username"));
        System.out.println(redisTemplate.hasKey("a:b:c:username"));
        System.out.println(redisTemplate.hasKey("book:list"));
        System.out.println(redisTemplate.opsForList().index("book:list", 0));
        System.out.println(redisTemplate.opsForValue().get("a:b:c:username"));
    }

    @Test
    public void Test20() {
        redisTemplate.move("a:b:c:username", 1);
        System.out.println(redisTemplate.opsForList().range("book:list", 0, 100));

    }

    @Test
    public void Test21() {
        User user = new User();
        user.setName("程");
        String a[] = {"1", "2", "3", "4"};
        redisTemplate.opsForSet().add("set1:set", "1");
        redisTemplate.opsForSet().add("set1:set", "2");
        redisTemplate.opsForSet().add("set1:set", "");
        redisTemplate.opsForSet().add("set1:set", " ");
        redisTemplate.opsForSet().add("set1:set", a);
        redisTemplate.opsForSet().add("set1:set", user.toString());
//        redisTemplate.opsForSet().remove("set1:set", a);
//        Object pop = redisTemplate.opsForSet().pop("set1:set");
//        System.out.println(pop);
    }

    @Test
    public void Test22() {
        redisTemplate.opsForSet().add("set2:set", s1);
        redisTemplate.opsForSet().add("set2:set", a);
        redisTemplate.opsForSet().add("set2:set", user.toString());
    }

    @Test
    public void Test23(){
        redisTemplate.opsForSet().add("set1", s1);
        redisTemplate.opsForSet().add("set1", s2);
        redisTemplate.opsForSet().add("set1", s3);
        redisTemplate.opsForSet().add("set1", s4);
        redisTemplate.opsForSet().add("set1", s5);
        redisTemplate.opsForSet().add("set1", user.toString());
//        redisTemplate.opsForZSet().add("set3:set", a, 1);
    }
    public void Test24(){

    }
    public void Test25(){

    }
    public void Test26(){

    }
    public void Test27(){

    }
    public void Test28(){

    }
    public void Test29(){

    }
    public void Test30(){

    }
    public void Test31(){

    }
    public void Test32(){

    }
    public void Test33(){

    }
    public void Test34(){

    }
    public void Test35(){

    }
    public void Test36(){

    }
    public void Test37(){
        master；
    }


}