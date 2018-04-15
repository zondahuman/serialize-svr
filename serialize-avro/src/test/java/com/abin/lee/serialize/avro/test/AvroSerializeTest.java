package com.abin.lee.serialize.avro.test;

import com.abin.lee.serialize.avro.AvroSerialize;
import com.abin.lee.serialize.avro.model.User;
import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by abin on 2018/4/15 18:15.
 * serialize-svr
 * com.abin.lee.serialize.avro.test
 */
public class AvroSerializeTest {

    @Test
    public void testAvro1() throws IOException {
//        User user = new User();
//        user.setCreateTime(new Date());
//        user.setId((int)(Math.random()*100));
//        user.setName("lee");
//        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        User user =  new User("Yanbin", "Chicago");
        System.out.println("user=" + JsonUtil.toJson(user));

        AvroSerialize avroSerialize = new AvroSerialize();
        byte[] result1 = avroSerialize.serialize(User.class, user);
        System.out.println("result1=" + result1);
        User userResult = (User) avroSerialize.deserialize(User.class, result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));

    }

    @Test
    public void testAvro2() throws IOException {
        Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));
        AvroSerialize avroSerialize = new AvroSerialize();

        byte[] byteResult = avroSerialize.serialize(User.class, param);
        System.out.println("byteResult=" + byteResult);
        Integer objectResult = (Integer)avroSerialize.deserialize(Integer.class, byteResult);
        System.out.println("objectResult=" + JsonUtil.toJson(objectResult));

    }


    @Data
    public static class UserBean {
        private Integer id;
        private String name;
        private BigDecimal price;
        private Date createTime;
    }


}
