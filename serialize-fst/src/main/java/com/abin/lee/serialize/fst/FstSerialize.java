package com.abin.lee.serialize.fst;

import org.nustaq.serialization.FSTConfiguration;

/**
 * Created by abin on 2018/4/15 17:14.
 * serialize-svr
 * com.abin.lee.serialize.fst
 * https://blog.csdn.net/z69183787/article/details/53005961
 */
public class FstSerialize {

    public static FSTConfiguration fstConfiguration = FSTConfiguration.createStructConfiguration();

    public static byte[] serialize(Object obj) {
        return fstConfiguration.asByteArray(obj);
    }

    public static Object deserialize(byte[] sec) {
        return fstConfiguration.asObject(sec);
    }

}
