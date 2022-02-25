import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王宁 2021/12/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class 文件的操作测试 {

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    @Test
    public void 删除文件() {
        String fileName = "/Users/wangning/Desktop/Snipaste_2022-02-24_10-08-44.png";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败：" + fileName + "文件不存在");
        } else {
            if (file.isFile()) {
                deleteFile(fileName);
            } else {
//                deleteDirectory(fileName);
            }
        }
    }

    @Test
    public void 文件目录() {
        String filepath = "/Users/wangning/Documents/文档/学习文档和视频/任务文档";//D盘下的file文件夹的目录
        File file = new File(filepath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
        if (fileList.length == 0) {
            System.out.println("没发现文件");
        }
        List<File> files = Arrays.asList(fileList);

        for (File file1 : files.stream().sorted().collect(Collectors.toList())) {
            System.out.println(file1.getName());
        }
    }
}
