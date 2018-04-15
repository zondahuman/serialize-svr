package com.abin.lee.serialize.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by abin on 2018/4/15 17:32.
 * serialize-svr
 * com.abin.lee.serialize.hessian
 */
public class HessianSerialize {


    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //Hessian的序列化输出
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(obj);
        byte[] output = byteArrayOutputStream.toByteArray();
        return output;
    }

    public static Object deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        //Hessian的反序列化读取对象
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        Object object = (Object) hessianInput.readObject();
        return object;
    }


}
