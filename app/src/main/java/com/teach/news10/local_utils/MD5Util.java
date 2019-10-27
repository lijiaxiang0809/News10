package com.teach.news10.local_utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by renxiaolong on 14/7/5.
 * MD5
 */

public class MD5Util {
    /**
     * 加密
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public final static String encrypt(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encryptDES(String encryptString) throws Exception {
        if (TextUtils.isEmpty(encryptString))
            return encryptString;
        byte[] key = "T3qAL3Mh".getBytes();
        byte[] iv = "RCh2M8xE".getBytes();
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec skey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        String encode = new String(Base64.encode(encryptedData, Base64.DEFAULT));
        if (TextUtils.isEmpty(encode))
            return encode;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encode.length(); i++) {
            int asc = encode.charAt(i);
            if (asc != 10 && asc != 13) {
                sb.append(encode.subSequence(i, i + 1));
            }
        }
        return sb.toString();
    }

    public static String decryptDES(String decryptString) throws Exception {
        byte[] key = "T3qAL3Mh".getBytes();
        byte[] iv = "RCh2M8xE".getBytes();
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec skey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
        byte[] encryptedData = cipher.doFinal(Base64.decode(decryptString.getBytes(), Base64.DEFAULT));
        return new String(encryptedData);
    }

    public static String decode(String message) {
        if (TextUtils.isEmpty(message)) return null;
        byte[] bytes = Base64.decode(message.getBytes(), Base64.DEFAULT);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return str;
        }
    }


    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
        }
    }

    //检查文件md5是否与原始的匹配
    public static boolean isFileMD5Matched(String filePath, String originalMD5) {
        try {
            InputStream fis;
            fis = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            int numRead;
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
            fis.close();
        } catch (Exception e) {
        }
        return originalMD5.equals(bufferToHex(messagedigest.digest(),
                0, messagedigest.digest().length));
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


}
