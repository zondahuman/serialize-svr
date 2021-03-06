package com.abin.lee.serialize.avro;

import com.abin.lee.serialize.avro.model.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by abin on 2018/4/15 18:05.
 * serialize-svr
 * com.abin.lee.serialize.avro
 * https://blog.csdn.net/hua245942641/article/details/50724360
 */
public class AvroSerialization<T> {

    public static byte[] serializeAvroToByteArray(User user) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user.getSchema(), baos);
        dataFileWriter.append(user);
        dataFileWriter.close();
        return baos.toByteArray();
    }

    public static User deserialzeAvroFromByteArray(byte[] usersByteArray) throws IOException {
        SeekableByteArrayInput sbai = new SeekableByteArrayInput(usersByteArray);
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(sbai, userDatumReader);
        System.out.println("----------------deserialzeAvroFromByteArray-------------------");
        User readUser = null;
        while (dataFileReader.hasNext()) {
            readUser = dataFileReader.next(readUser);
            System.out.println(readUser);
        }
        return readUser;
    }

    public static byte[] serialize(Class<?> object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DatumWriter<?> userDatumWriter = new SpecificDatumWriter<>(object.getClass());
        DataFileWriter<?> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        Schema schema= new Schema.Parser().parse(AvroSerialization.class.getResourceAsStream("user.avsc"));
        dataFileWriter.create(schema, baos);
//        dataFileWriter.append(object);
        dataFileWriter.close();
        return baos.toByteArray();
    }

    public static User deserialze(byte[] usersByteArray) throws IOException {
        SeekableByteArrayInput sbai = new SeekableByteArrayInput(usersByteArray);
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(sbai, userDatumReader);
        System.out.println("----------------deserialzeAvroFromByteArray-------------------");
        User readUser = null;
        while (dataFileReader.hasNext()) {
            readUser = dataFileReader.next(readUser);
            System.out.println(readUser);
        }
        return readUser;
    }

}
