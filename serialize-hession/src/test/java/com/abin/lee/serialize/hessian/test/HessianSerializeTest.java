package com.abin.lee.serialize.hessian.test;

import com.abin.lee.serialize.hessian.HessianSerialize;
import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by abin on 2018/4/15 17:42.
 * serialize-svr
 * com.abin.lee.serialize.hessian.test
 */
public class HessianSerializeTest {

    @Test
    public void testHessian1() throws IOException {
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        System.out.println("user=" + JsonUtil.toJson(user));

        byte[] result1 = HessianSerialize.serialize(user);
        System.out.println("result1=" + result1);
        User userResult = (User)HessianSerialize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));

    }

    @Test
    public void testHessian2() throws IOException {
        Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));

        byte[] byteResult = HessianSerialize.serialize(param);
        System.out.println("byteResult=" + byteResult);
        Integer objectResult = (Integer)HessianSerialize.deserialize(byteResult);
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
