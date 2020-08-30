package com.panrui.panrui.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MD5Utils {

    public String setCode(String str) {
        try {
            /**
             * MD5加密类
             *
             * @param str 要加密的字符串
             * @return 加密后的字符串
             */
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] byteDigest = messageDigest.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte b : byteDigest) {
                i  = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            messageDigest.reset();
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }



    }
}
