package com.abin.lee.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.Arrays;

/**
 * Created by abin on 2018/4/15 15:20.
 * serialize-svr
 * com.abin.lee.serialize.kryo
 * https://blog.csdn.net/derrantcm/article/details/72861680
 */
public class KryoSerizlize {
    private static Kryo kryo = new Kryo();

    public static byte[] serialize(Object object)  {

        System.out.println("[serialize]" + object);

        byte[] buffer = new byte[2048];
        Output output = new Output(buffer);
        kryo.writeClassAndObject(output, object);
        return output.toBytes();
    }

    public static Object deserialize(byte[] bytes)  {

        System.out.println("[deserialize]" + Arrays.toString(bytes));

        Input input = new Input(bytes);
        @SuppressWarnings("unchecked")
        Object t = (Object) kryo.readClassAndObject(input);
        return t;
    }


}
