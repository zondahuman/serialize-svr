package com.abin.lee.serialize.avro.test;

import com.abin.lee.serialize.avro.AvroSerialization;
import com.abin.lee.serialize.avro.AvroSerialize;
import com.abin.lee.serialize.avro.AvroSerializer;
import com.abin.lee.serialize.avro.model.User;
import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by abin on 2018/4/16 0:15.
 * serialize-svr
 * com.abin.lee.serialize.avro.test
 */
public class AvroSerializationTest {

    @Test
    public void testAvro1() throws IOException {
//        User user = new User();
//        user.setCreateTime(new Date());
//        user.setId((int)(Math.random()*100));
//        user.setName("lee");
//        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        User user =  new User("Yanbin", "Chicago");
        System.out.println("user=" + JsonUtil.toJson(user));

        byte[] result1 = AvroSerialization.serializeAvroToByteArray(user);
        System.out.println("result1=" + result1);
        User userResult =  AvroSerialization.deserialzeAvroFromByteArray(result1);
        System.out.println("userResult=" + userResult);

    }

    @Test
    public void testAvro2() throws IOException {
        Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));
        AvroSerialize avroSerialize = new AvroSerialize();

//        byte[] byteResult = AvroSerialization.serializeAvroToByteArray(param);
//        System.out.println("byteResult=" + byteResult);
//        Integer objectResult = (Integer)AvroSerialization.deserialzeAvroFromByteArray(byteResult);
//        System.out.println("objectResult=" + JsonUtil.toJson(objectResult));

    }


    @Data
    public static class UserBean {
        private Integer id;
        private String name;
        private BigDecimal price;
        private Date createTime;
    }


}
