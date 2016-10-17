package com.sirding.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Java版的 Serialize
 * @author zc.ding
 * @date 2016年10月18日
 *
 */
public class SerializeUtil {

	/**
	 * <p>
	 * 	将对象进行序列化操作
	 * <p>
	 * @param value
	 * @return
	 * @author zc.ding
	 * @date 2016年10月18日
	 */
    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("serialize error");
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    /**
     * <p>
     * 	对象的反序列化
     * <p>
     * @param in
     * @param clazz
     * @return
     * @author zc.ding
     * @date 2016年10月18日
     */
    public static <T> T deserialize(byte[] in, Class<T> clazz) {
    	T result = null;
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
                result = clazz.newInstance();
                BeanUtils.copyProperties(result, rv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("deserialize error");
        } finally {
            close(is);
            close(bis);
        }
        return result;
    }

    /**
     * <p>
     * 	关闭流
     * <p>
     * @param closeable
     * @author zc.ding
     * @date 2016年10月18日
     */
    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("close stream error");
            }
    }

}
