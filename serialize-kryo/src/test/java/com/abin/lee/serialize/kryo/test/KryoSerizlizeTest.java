package com.abin.lee.serialize.kryo.test;

import avro.shaded.com.google.common.collect.Maps;
import com.abin.lee.serialize.kryo.KryoSerizlize;
import com.abin.lee.serialize.kryo.common.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by abin on 2018/4/15 15:21.
 * serialize-svr
 * com.abin.lee.serialize.kryo.test
 */
public class KryoSerizlizeTest {


    @Test
    public void testKryo1(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        System.out.println("user=" + JsonUtil.toJson(user));

        byte[] result1 = KryoSerizlize.serialize(user);
        System.out.println("result1=" + result1);
        User userResult = (User)KryoSerizlize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));
    }

    @Test
    public void testKryo2(){
       Integer param = 500;
        System.out.println("param=" + JsonUtil.toJson(param));

        byte[] byteResult = KryoSerizlize.serialize(param);
        System.out.println("byteResult=" + byteResult);
        Integer objectResult = (Integer)KryoSerizlize.deserialize(byteResult);
        System.out.println("objectResult=" + JsonUtil.toJson(objectResult));
    }

    @Test
    public void testKryo3(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", 1);
        map.put("name", "lee");
        map.put("createTime", new Date());
        System.out.println("map=" + JsonUtil.toJson(map));
        byte[] result1 = KryoSerizlize.serialize(map);
        System.out.println("result1=" + result1);
        Map<String, Object> userResult = (Map<String, Object>)KryoSerizlize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));
    }

    @Test
    public void testKryo4(){
        Map<String, Object> map = Maps.newHashMap();
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        map.put("user", user);

        System.out.println("map=" + JsonUtil.toJson(map));
        byte[] result1 = KryoSerizlize.serialize(map);
        System.out.println("result1=" + result1);
        Map<String, Object> userResult = (Map<String, Object>)KryoSerizlize.deserialize(result1);
        System.out.println("userResult=" + JsonUtil.toJson(userResult));
    }


    @Test
    public void testKryo5(){
        Map<String, Object> map = Maps.newHashMap();
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        map.put("user", user);

        System.out.println("map=" + JsonUtil.toJson(map));
        byte[] result1 = KryoSerizlize.serializeObject(map);
        System.out.println("result1=" + result1);
        Map<String, Object> userResult = (Map<String, Object>)KryoSerizlize.deserializeObject(result1, map.getClass());
        System.out.println("userResult=" + JsonUtil.toJson(userResult));
    }


    @Test
    public void testKryo6(){
        Map<String, Object> map = Maps.newHashMap();
        User user = new User();
        user.setCreateTime(new Date());
        user.setId((int)(Math.random()*100));
        user.setName("lee");
        user.setPrice(new BigDecimal("" + (int)(Math.random()*10000)));
        map.put("user", user);

        System.out.println("map=" + JsonUtil.toJson(map));
        byte[] result1 = KryoSerizlize.serializeRegistry(map, map.getClass());
        System.out.println("result1=" + result1);
//        Map<String, Object> userResult = (Map<String, Object>)KryoSerizlize.deserializeRegistry(result1, map.getClass());
        Object userResult = (Object)KryoSerizlize.deserializeRegistry(result1, map.getClass());
        System.out.println("userResult=" + JsonUtil.toJson(userResult));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User implements Serializable {
        private Integer id;
        private String name;
        private BigDecimal price;
        private Date createTime;

    }



}
