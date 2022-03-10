import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lazada.lazop.api.LazopClient;
import com.lazada.lazop.api.LazopRequest;
import com.lazada.lazop.api.LazopResponse;
import com.lazada.lazop.util.ApiException;
import org.junit.Test;

import java.util.Optional;

public class json字符串测试 {
//    public static void main(String[] args) {
//        User user = new User();
//        List<Object> list = new ArrayList<>();
//        List<User> list2 = new ArrayList<>();
//        List<String> list3 = new ArrayList<>();
//
//        list.add(new Team(2L, "chen", "qingdao"));
//        list.add(new Team(3L, "chen", "qingdao"));
//        list.add(1);
//        list.add("nihao");
//        user.setList(list);
//        user.setAge(12);
//        user.setName("zhang");
//        user.setTeam(new Team(2L, "chen", "qingdao"));
//        user.setId(1L);
//        System.out.println(JSON.toJSON(user).toString());
//
//        System.out.println(JSON.toJSON(list).toString());
//
//        JSON jsonObject = JSON.parseObject(JSON.toJSON(user).toString());
//        User user1 = JSON.toJavaObject(jsonObject, User.class);
//        System.out.println(user1);
//        list2.add(user);
//        list3.add("user");
//        JSON jsonObject2 = JSON.parseObject(JSON.toJSON(list2).toString());
//        List list1 = JSON.toJavaObject(jsonObject2, List.class);
//        System.out.println(list1);
//
//    }

    public static void main(String[] args) throws ApiException, InterruptedException {
        String url = "https://api.lazada.com.my/rest";
        String appkey = "106910";
        String appSecret = "aJzraVsOcsMarXYJOXzheXirBVDUCXW9";
        String accessToken = "50000600a27NQw9iBNyqPg9YFYcKPauuEBdES6o1d68452aRyCmdNH4kuJC4omGs";

        LazopClient client = new LazopClient(url, appkey, appSecret);
        LazopRequest request = new LazopRequest();
        request.setApiName("/order/document/awb/html/get");
        request.setHttpMethod("GET");
        request.addApiParameter("order_item_ids", "[323438867703989]");
        LazopResponse response = client.execute(request, accessToken);
        Thread.sleep(10);
        System.out.println(response.getBody());
    }

    @Test
    public void getJsonAttribute() {
        String jsonString = "{\n" +
                "\"data\": {\n" +
                "\"document\": {\n" +
                "\"file\": \"Hello,My Word!\",\n" +
                "\"mime_type\": \"text/html\",\n" +
                "\"document_type\": \"shippingLabel\"\n" +
                "}\n" +
                "},\n" +
                "\"code\": \"0\",\n" +
                "\"request_id\": \"2101105a16466476862501901\",\n" +
                "\"age\": 12\n" +
                "}";

        JSONObject jsonObj = JSON.parseObject(jsonString);

        // 赋值下面的，如果是对象，则放入getJSONObject里，如果不是对象(甚至是数字也可以)，放入getString里
        // 由于用的是Optional，所以找不到不报错，只会返回null
        System.out.println(Optional.ofNullable(jsonObj)
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getJSONObject("data")))
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getJSONObject("document")))
                .orElse(null));


        System.out.println(Optional.ofNullable(jsonObj)
                .flatMap(jsonObject -> Optional.ofNullable(jsonObject.getInteger("age")))
                .orElse(null));
    }
}
