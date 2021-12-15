import com.wn.RandomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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

        double d2 =  1.79769313486231570E+308;

        float f1 = -3.4E+38F;


        double d3 = 0;

        BigDecimal decimal1 =  new BigDecimal(f1);
        BigDecimal decimal2 =  new BigDecimal(f1);

        System.out.println(decimal1);
        System.out.println(decimal2);

    }
}
