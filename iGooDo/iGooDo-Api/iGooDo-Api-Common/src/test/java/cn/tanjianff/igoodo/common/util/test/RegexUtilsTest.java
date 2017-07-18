package cn.tanjianff.igoodo.common.util.test;

import cn.tanjianff.igoodo.common.util.RegexUtils;
import org.junit.Test;

/**
 * Created by tanjian on 2017/7/16.
 */
public class RegexUtilsTest {
    @Test
    public void isChinaPhoneLegal() {
        String phone = "18323261979";
        System.out.println(RegexUtils.isChinaPhoneLegal(phone));
    }
}
