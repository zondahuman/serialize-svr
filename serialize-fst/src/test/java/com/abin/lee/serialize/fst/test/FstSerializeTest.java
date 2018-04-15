package com.abin.lee.serialize.fst.test;

import com.abin.lee.serialize.fst.FstSerialize;
import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by abin on 2018/4/15 17:18.
 * serialize-svr
 * com.abin.lee.serialize.fst.test
 */
public class FstSerializeTest {

    @Test
    public void testFst1(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        System.out.println("user=" + JsonUtil.toJson(user));

        byte[] result1 = FstSerialize.serialize(user);
        System.out.println("result1=" + result1);
        User userResult = (User)FstSerialize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));

    }

    @Test
    public void testFst2(){
        Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));

        byte[] byteResult = FstSerialize.serialize(param);
        System.out.println("byteResult=" + byteResult);
        Integer objectResult = (Integer)FstSerialize.deserialize(byteResult);
        System.out.println("objectResult=" + JsonUtil.toJson(objectResult));

    }


    @Data
    public static class User{
        private Integer id;
        private String name;
        private BigDecimal price;
        private Date createTime;
    }
}
