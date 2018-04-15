package com.abin.lee.serialize.avro;

import com.abin.lee.serialize.avro.model.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by abin on 2018/4/15 18:05.
 * serialize-svr
 * com.abin.lee.serialize.avro
 * https://unmi.cc/apache-avro-serializing-deserializing/#more-7488
 * https://blog.csdn.net/hua245942641/article/details/50724360
 * 貌似还有些问题
 */
public class AvroSerializer {

    public static User deserialize(byte[] data) throws IOException {
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        BinaryDecoder binaryEncoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
        return userDatumReader.read(new User(), binaryEncoder);
    }

    public static byte[] serialize(User user) throws IOException {
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
        userDatumWriter.write(user, binaryEncoder);
        return outputStream.toByteArray();
    }


}
