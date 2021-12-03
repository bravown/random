import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wn.RandomApplication;
import com.wn.mapper.UserMapper;
import org.apache.commons.collections.MapUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest {

    @Autowired
    private UserMapper userMapper;

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
    public void Test4(){
        Map<Integer, String> map = new HashMap<>();
        boolean notEmpty = MapUtils.isNotEmpty(map);
        map.put(1,"我");
        boolean notEmpty1 = MapUtils.isNotEmpty(map);
        System.out.println(notEmpty+""+notEmpty1);
    }

    @Test
    public void Test5(){
        String s1 = "123";
        String s2 = "123 ";
        String s3 = " ";
        String s4 = "";
    }

}
