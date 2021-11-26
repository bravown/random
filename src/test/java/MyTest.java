import com.wn.RandomApplication;
import com.wn.mapper.UserMapper;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王宁 2021/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class MyTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void Test1(){
        Map<Integer,String> map = new HashMap<>();
        Integer count = new Integer(1);
        map.put(count,"wang");
        map.put(count,"ol");
    }

    @Test
    public void Test2(){

    }

    @Test
    public void Test3()throws Exception{
        Connection connect = Jsoup.connect("https://blog.csdn.net/javazejian/article/details/71333103");
        Document doc = connect.post();
//        System.out.println(doc);
        Elements element = doc.select("#blogColumnPayAdvert span");
        System.out.println(element);
        for (Element element1:element){
            String href = element1.attr("String");
            System.out.println("输出的是:"+href);
        }

    }
}
