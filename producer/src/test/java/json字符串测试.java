import com.alibaba.fastjson.JSON;
import com.wn.entity.Team;
import com.wn.entity.User;

import java.util.ArrayList;
import java.util.List;

public class json字符串测试 {
    public static void main(String[] args) {
        User user = new User();
        List<Object> list = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list.add(new Team(2L, "chen", "qingdao"));
        list.add(new Team(3L, "chen", "qingdao"));
        list.add(1);
        list.add("nihao");
        user.setList(list);
        user.setAge(12);
        user.setName("zhang");
        user.setTeam(new Team(2L, "chen", "qingdao"));
        user.setId(1L);
        System.out.println(JSON.toJSON(user).toString());

        System.out.println(JSON.toJSON(list).toString());

        JSON jsonObject = JSON.parseObject(JSON.toJSON(user).toString());
        User user1 = JSON.toJavaObject(jsonObject, User.class);
        System.out.println(user1);
        list2.add(user);
        list3.add("user");
        JSON jsonObject2 = JSON.parseObject(JSON.toJSON(list2).toString());
        List list1 = JSON.toJavaObject(jsonObject2, List.class);
        System.out.println(list1);

    }
}
