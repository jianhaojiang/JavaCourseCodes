package com.jjh.jvmweek1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * @version 1.0
 * @description
 * @author: jjh 1711449651@qq.com
 * @date 2021/11/3 12:34
 **/
public class HelloClassLoader extends ClassLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloClassLoader.class);

    public static void main(String[] args) throws Exception {
        Class<?> helloClass = new HelloClassLoader().findClass("Hello");
        Method helloMethod = helloClass.getMethod("hello");
        helloMethod.invoke(helloClass.newInstance());
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(
                    ResourceUtils.getFile("classpath:hello.xlass"));
            bytes = decode(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            LOGGER.error("个性化ClassLoader异常:", e);
        }
        return super.findClass(name);
    }

    /**
     * 解密：对byte[] 每一位进行 255-x
     *
     * @param bytes
     * @return
     */
    protected byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }


}
