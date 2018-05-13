package com.abin.lee.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;

import java.util.Arrays;

/**
 * Created by abin on 2018/4/15 15:20.
 * serialize-svr
 * com.abin.lee.serialize.kryo
 * https://blog.csdn.net/derrantcm/article/details/72861680
 * https://www.cnblogs.com/holoyong/p/7420880.html
 * https://blog.csdn.net/rocklee/article/details/26451739
 */
public class KryoSerizlize {
    private static Kryo kryo = new Kryo();

    static {
        SynchronizedCollectionsSerializer.registerSerializers(kryo);
        kryo.addDefaultSerializer(java.lang.Throwable.class, new JavaSerializer());
    }

    /**
     * 第一组采用writeObject, readObject
     * 1，kryo.writeObject，这种方法只会序列化对象实例，而不会记录对象所属类的任何信息。优势是最节省空间，劣势是在反序列化时，需要提供类作为模板才能顺利反序列。反序列化时使用readObject。
     * @param object
     * @return
     */
    public static byte[] serializeObject(Object object)  {
        System.out.println("[serialize]" + object);
        byte[] buffer = new byte[2048];
        Output output = new Output(buffer);
        kryo.writeObject(output, object);
        return output.toBytes();
    }

    public static <T> T  deserializeObject(byte[] bytes, Class<T> clazz)  {
        System.out.println("[deserialize]" + Arrays.toString(bytes));
        Input input = new Input(bytes);
        @SuppressWarnings("unchecked")
        T t = (T) kryo.readObject(input, clazz);
        return t;
    }


    /**
     * 2，直接kryo.writeClassAndObject，这种方法会先将对象所属类的全限定名序列化，然后再依次序列化对象实例的成员。优势是完全动态序列化，整个kryo周期都不需要提供类信息。反序列化时使用readClassAndObject
     * @param object
     * @return
     */
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


    /**
     * 3，先注册，再kryo.writeClassAndObject，这种方式时最理想的，其结合了前两种优势，又有效规避了劣势，事先将需要序列化的类注册给kryo（此时类和唯一id绑定），
     * 之后使用writeClassAndObject序列化时，只会序列化注册id，而不会序列化类的全限定名了，这样大大节省了空间（通常只比writeObject多一个字节）。
     * 反序列化时使用readClassAndObject。注意序列化和反序列的kryo的注册信息应当保持一致。
     * @param object
     * @return
     */
    public static byte[] serializeRegistry(Object object, Class<?> clazz)  {
        System.out.println("[serialize]" + object);
        kryo.register(clazz.getClass(),new BeanSerializer(kryo, clazz.getClass()));
        byte[] buffer = new byte[2048];
        Output output = new Output(buffer);
        kryo.writeObject(output, object);
        return output.toBytes();
    }

    public static Object deserializeRegistry(byte[] bytes, Class<?> clazz)  {
        System.out.println("[deserialize]" + Arrays.toString(bytes));
//        kryo.setRegistrationRequired(true);
        kryo.register(clazz.getClass(),new BeanSerializer(kryo, clazz.getClass()));
//        kryo.register(clazz.getClass());
//        SynchronizedCollectionsSerializer.registerSerializers(kryo);
        Input input = new Input(bytes);
        @SuppressWarnings("unchecked")
        Object t = (Object)kryo.readObject(input, clazz.getClass());
        return t;
    }




}
