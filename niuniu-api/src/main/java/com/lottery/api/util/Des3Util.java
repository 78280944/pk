package com.lottery.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 3DES加密工具类
 */
public class Des3Util {
    // 密钥 长度不得小于24
    private String secretKey;
    // 向量 可有可无 终端后台也要约定
    private String iv;
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    public Des3Util() {
        try {
            Properties prop = new Properties();
            InputStream in = Des3Util.class.getClassLoader().getResourceAsStream("rest-config.properties");
            prop.load(in);
            secretKey = prop.getProperty("des3.secretKey").trim();
            iv = prop.getProperty("des3.iv").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3DES加密
     *
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public String encode(String plainText){
        String encodedStr = "";
        try{
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
    
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            encodedStr = new String(Base64.encodeBase64(encryptData));
        }catch(Exception e){
            e.printStackTrace();
        }
        return encodedStr;
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     *            加密文本
     * @return
     * @throws Exception
     */
    public String decode(String encryptText){
        String encodedStr = "";
        try{
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            byte[] decryptData = cipher.doFinal(Base64.decodeBase64(encryptText));
            encodedStr = new String(decryptData, encoding);
        }catch(Exception e){
            e.printStackTrace();
        }
        return encodedStr;
    }

    public static void main(String args[]) throws Exception {
        String str = "puzongsoft2qhjr";
        System.out.println("----加密前-----：" + str);
        Des3Util des3Util = new Des3Util();
        String encodeStr = des3Util.encode(str);
        System.out.println("----加密后-----：" + encodeStr);
        System.out.println("----解密后-----：" + des3Util.decode(encodeStr));
    }
}