package com.ysdemo.mykotlin;

import com.ysdemo.mykotlin.util.JsonTestUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Song on 2017/9/22.
 */

public class JsonTestUtilTest {
    @Test
    public void testUpCaseKey() {
        Assert.assertTrue(new JsonTestUtil().testUpCaseKey());
    }
}
