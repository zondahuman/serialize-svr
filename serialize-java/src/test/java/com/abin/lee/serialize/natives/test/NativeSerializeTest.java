package com.abin.lee.serialize.natives.test;

import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import com.abin.lee.serialize.natives.NativeSerialize;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by abin on 2018/4/15 17:46.
 * serialize-svr
 * com.abin.lee.serialize.natives.test
 */
public class NativeSerializeTest {
    @Test
    public void testNative1(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        System.out.println("user=" + JsonUtil.toJson(user));

        byte[] result1 = NativeSerialize.serialize(user);
        System.out.println("result1=" + result1);
        User userResult = (User)NativeSerialize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));

    }

    @Test
    public void testNative2(){
        Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));

        byte[] byteResult = NativeSerialize.serialize(param);
        System.out.println("byteResult=" + byteResult);
        Integer objectResult = (Integer)NativeSerialize.deserialize(byteResult);
        System.out.println("objectResult=" + JsonUtil.toJson(objectResult));

    }


    @Data
    public static class User implements java.io.Serializable{
        private Integer id;
        private String name;
        private BigDecimal price;
        private Date createTime;
    }
}
