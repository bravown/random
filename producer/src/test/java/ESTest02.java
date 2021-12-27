import com.wn.RandomApplication;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author 王宁 2021/12/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class ESTest02 {

    @Before
    public static RestHighLevelClient getClient() {
        //创建Httphost
        HttpHost httpHost = new HttpHost("127.0.0.1", 9200);
        //创建RestClientBuilder
        RestClientBuilder clientBuilder = RestClient.builder(httpHost);
        //创建RestHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);
        return client;
    }

    @Test
    public void Test01() {

    }

    public class Test02 {
        String index = "person";
        String type = "man";

        @Test
        //创建一个索引
        public void testCreateIndex() throws IOException {
            //创建一个索引settings
            //shards:分片数
            Settings.Builder settings = Settings.builder().
                    put("number_of_shards", 3)
                    .put("number_of_replicas", 1);
            //准备一个索引的mappings
            XContentBuilder mappings = JsonXContent.contentBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("name")
                    .field("type", "text")
                    .endObject()
                    .startObject("age")
                    .field("type", "long")
                    .endObject()
                    .startObject("birthday")
                    .field("type", "date")
                    .field("format", "yyyy-MM-dd")
                    .endObject()
                    .endObject()
                    .endObject();

            //settings和mappings对象创建完了,封装到request对象
            CreateIndexRequest request = new CreateIndexRequest(index).settings(settings).mapping(type, mappings);
            //通过client对象连接es,发送请求执行创建索引
            CreateIndexResponse response = ESTest02.getClient().indices().create(request, RequestOptions.DEFAULT);
            //输出结果日志
            System.out.println("结果" + response.toString());
        }

        @Test
        public void testIfExist() throws Exception {
            //封装request对象
            GetIndexRequest request = new GetIndexRequest();
            request.indices(index);
            //通过client发送请求
            boolean ifExist = ESTest02.getClient().indices().exists(request, RequestOptions.DEFAULT);
            //返回结果
            System.out.println(ifExist);
        }
    }
}
