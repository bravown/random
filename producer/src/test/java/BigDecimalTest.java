import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 王宁 2021/12/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomApplication.class)
public class BigDecimalTest {

    @Test
    public void Test1() {

        Long l1 = -9223372036854775808L;
        Long l2 = 9223372036854775807L;

        double d1 = -1.79769313486231570E+308;

        double d2 = 1.79769313486231570E+308;

        Float f1 = -3.4E+38F;


        double d3 = 0;

        BigDecimal decimal1 = new BigDecimal(f1);
        BigDecimal decimal2 = new BigDecimal(f1);

        System.out.println(decimal1);
        System.out.println(decimal2);

    }

    @Test
    public void Test2() {

        String s1 = "3.14159263543653654654654634564346523756747367";
        String s2 = "32432534";
        BigDecimal a = new BigDecimal(s1);
        BigDecimal b = new BigDecimal(s2);
        BigDecimal formatData = a.divide(b, 999, RoundingMode.HALF_UP);
        System.out.println(formatData);
        String s = formatData(formatData);
        System.out.println(s);

    }

    @Test
    public void Test3(){
        float f1 = 0.1f;
        float f2 = 0.2f;
        float f3 = f1+f2;
        System.out.println(f3);
        BigDecimal bigDecimal = new BigDecimal(1);

    }

    /**
     * 超过8位就用科学计数法
     *
     * @param big 传进一个需要格式化的BigDecimal
     * @return 返回格式化后字符串
     */
    public static String formatData(BigDecimal big) {

        // 删除小数点的尾随0，并转成字符串
        String strAll = big.stripTrailingZeros().toPlainString();

        // 获取该字符串的纯数字长度（包含负号）
        int strLength = strAll.contains(".") ? strAll.length() - 1 : strAll.length();


        if (strLength > 8) {
            // 用科学计数法就再删除尾随0
            String string = big.stripTrailingZeros().toString();
            if (string.contains("E")) {
                // 返回此字符串中第一次出现指定子字符串的索引。返回的索引是最小值k
                int index = string.indexOf("E");
                if ((string.charAt(index + 1) + "").equals("-")) {
                    return string;
                } else {
                    return new StringBuilder().append(string.substring(0, index + 1))
                            .append(string.substring(index + 2, string.length())).toString();
                }
            } else {
                return string;
            }
        } else {
            return strAll;
        }
    }
}
