package com.abin.lee.serialize.avro;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.collections.iterators.ObjectArrayIterator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by abin on 2018/4/15 18:05.
 * serialize-svr
 * com.abin.lee.serialize.avro
 * https://unmi.cc/apache-avro-serializing-deserializing/#more-7488
 * https://blog.csdn.net/hua245942641/article/details/50724360
 * 貌似还有些问题
 */
public class AvroSerialize {

    public static byte[] serialize(Class<?> clazz, Object obj) throws IOException {
        DatumWriter datumWriter = new SpecificDatumWriter(clazz);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
        datumWriter.write(obj, binaryEncoder);
        return outputStream.toByteArray();
    }

    public static <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        DatumReader datumReader = new SpecificDatumReader(clazz.getClass());
        BinaryDecoder binaryEncoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(bytes), null);
        return (T)datumReader.read(clazz.getClass(), binaryEncoder);
    }


}
