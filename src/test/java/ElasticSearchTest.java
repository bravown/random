import com.alibaba.fastjson.JSON;
import com.wn.RandomApplication;
import com.wn.entity.User;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 索引(index)       ----数据库
 * 类型(type)        ----表
 * 文档(document)    ----表中的记录(行row)
 * 属性、字段(field)  ----列
 *
 * @author 王宁 2021/12/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 功能描述：createIndex 测试索引的创建
     */
    @Test
    public void createIndexTest() throws IOException {

        // 1. 创建索引请求
        CreateIndexRequest firstIndex = new CreateIndexRequest("o2_index");

        // 2. 客户端执行创建索引的请求
        CreateIndexResponse response = restHighLevelClient.indices().create(firstIndex, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 功能描述：existsIndexTest 判断索引是否存在
     */
    @Test
    public void existsIndexTest() throws IOException {

        // 1. 创建一个get请求获取指定索引的信息
        GetIndexRequest getIndexRequest = new GetIndexRequest("o2_index");

        // 2. 客户端执行请求判断索引是否存在
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
        if (!exists) {
            System.out.println(">>>>>>>>> 索引不存在。。。。。");
            return;
        }
    }

    /**
     * 功能描述：deleteIndexTest 删除指定的索引
     */
    @Test
    public void deleteIndexTest() throws IOException {

        // 1. 创建一个delete请求，用于删除索引信息
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("o2_index");

        // 2. 客户端执行删除请求
        AcknowledgedResponse result = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSON(result));
    }

    /**
     * 功能描述：创建文档
     */
    @Test
    public void addDocumentTest() throws IOException {
        // 1. 创建对象
        User user = new User(1L, "oliver", 27);

        // 2. 创建请求并指定索引
        IndexRequest indexRequest = new IndexRequest("o1_index");

        // 3. 创建的规则：put /o1_index/_doc/1
        indexRequest.id("1");            // 设置ID
        indexRequest.timeout("1s");      // 设置超时时间

        // 4. 将数据放入到请求中
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        // 5. 使用客户端发送请求
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(index));
    }

    /**
     * 功能描述：getDocumentTest 获取文档信息
     */
    @Test
    public void getDocumentTest() throws IOException {

        // 1. 创建请求信息绑定索引和指定需要查询的文档id
        GetRequest getRequest = new GetRequest("o1_index", "1");

        // 设置不获取的返回时的_source的上下文，一般情况是不需要设置的
//        getRequest.fetchSourceContext(new FetchSourceContext(false)).storedFields("_none_");

        // 2. 判断指定的索引和id是否存在
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        if (!exists) {
            System.out.println(">>>>>>>> 当前索引：o1_index对应的id：1 不存在");
            return;
        }
        System.out.println(">>>>>>>> 当前索引：o1_index对应的id：1 存在");

        // 3. 获取指定ID的资源信息
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        // 4. 打印获取到的资源信息
        System.out.println(response.getSourceAsString());
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 功能描述：updateDocmentTest 更新文档信息
     */
    @Test
    public void updateDocmentTest() throws IOException {

        // 1. 创建一个更新请求的信息，绑定索引名称和需要更新的文档ID
        UpdateRequest updateRequest = new UpdateRequest("o1_index", "1");
        updateRequest.timeout("1s");     // 设置超时时间
        User user = new User(45L, "小明", 28);

        // 2. 封装需要更新的文档信息
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);

        // 3. 使用update更新文档
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(updateResponse));
    }

    /**
     * 功能描述：deleteDocmentTest 删除文档信息
     */
    @Test
    public void deleteDocmentTest() throws IOException {

        // 1. 创建一个删除的请求，绑定索引名和需要删除的文档ID
        DeleteRequest deleteRequest = new DeleteRequest("o1_index", "1");

        // 2. 发起删除请求
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 功能描述：addDocmentByBatchTest 批量创建文档
     */
    @Test
    public void addDocmentByBatchTest() throws IOException {

        // 1. 创建批量的请求
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");     //  设置超时时间

        List<User> list = new ArrayList<>();
        User user = null;
        for (int i = 1; i <= 10; i++) {
            user = new User((long) i, "姓名" + i, 20 + i);
            list.add(user);
        }

        // 2. 将多条数据批量的放入bulkRequest中
        for (int i = 0; i < list.size(); i++) {
            // 批量更新和批量删除在这里修改对应的请求即可
            bulkRequest.add(new IndexRequest("o1_index")
                    .id("" + i)
                    .source(JSON.toJSONString(list.get(i)), XContentType.JSON)
            );
        }

        // 3. 执行批量创建文档
        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(responses.hasFailures());    // 是否失败，如果false则表示全部成功
        System.out.println(JSON.toJSONString(responses));
    }

    /**
     * 功能描述：searchTest 批量搜索，可以设置高亮等信息
     */
    @Test
    public void searchTest() throws IOException {

        // 1. 创建批量搜索请求，并绑定索引
        SearchRequest searchRequest = new SearchRequest("o1_index");

        // 2. 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 【注意：这里有个坑，当中文查询时，如果使用ik分词器会查询不到数据，属性需要使用xxx.keyword才能查询到数据】
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", "姓名7"); // 设置精确查询
//        QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder).timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 3. 将查询条件放入搜索请求request中
        searchRequest.source(sourceBuilder);

        // 4. 发起查询请求获取数据
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
        System.out.println(JSON.toJSONString(response.getHits().getHits()));
    }


    /*查看所有索引、类型。文档*/
    @Test
    public void Test1() throws IOException {

    }
}
