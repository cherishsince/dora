//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final byte[] data = new byte[]{0, 0, 0, 7, 115, 115, 104, 45, 114, 115, 97};

    public RSAUtil() {
    }

    public static String getPublicKeyStr(Map<String, Object> keyMap) throws Exception {
        Key key = (Key)keyMap.get("RSAPublicKey");
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKeyStr(Map<String, Object> keyMap) throws Exception {
        Key key = (Key)keyMap.get("RSAPrivateKey");
        return encryptBASE64(key.getEncoded());
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static byte[] sign(byte[] data, String privateKeyStr) throws Exception {
        PrivateKey priK = getPrivateKey(privateKeyStr);
        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initSign(priK);
        sig.update(data);
        return sig.sign();
    }

    public static boolean verify(byte[] data, byte[] sign, String publicKeyStr) throws Exception {
        PublicKey pubK = getPublicKey(publicKeyStr);
        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(sign);
    }

    public static byte[] encrypt(byte[] plainText, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        int inputLen = plainText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 117) {
            byte[] cache;
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(plainText, offSet, 117);
            } else {
                cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptText = out.toByteArray();
        out.close();
        return encryptText;
    }

    public static byte[] decrypt(byte[] encryptText, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        int inputLen = encryptText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 128) {
            byte[] cache;
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptText, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptText, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] plainText = out.toByteArray();
        out.close();
        return plainText;
    }

    public static byte[] encodePublicKey(RSAPublicKey key) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] sshrsa = new byte[]{0, 0, 0, 7, 115, 115, 104, 45, 114, 115, 97};
        out.write(sshrsa);
        BigInteger e = key.getPublicExponent();
        byte[] data = e.toByteArray();
        encodeUInt32(data.length, out);
        out.write(data);
        BigInteger m = key.getModulus();
        data = m.toByteArray();
        encodeUInt32(data.length, out);
        out.write(data);
        return out.toByteArray();
    }

    public static void encodeUInt32(int value, OutputStream out) throws IOException {
        byte[] tmp = new byte[]{(byte)(value >>> 24 & 255), (byte)(value >>> 16 & 255), (byte)(value >>> 8 & 255), (byte)(value & 255)};
        out.write(tmp);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.genKeyPair();
        byte[] publicKey = encodePublicKey((RSAPublicKey)keyPair.getPublic());
        System.err.println(Base64.getEncoder().encodeToString(publicKey));
    }

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap(2);
        keyMap.put("RSAPublicKey", publicKey);
        keyMap.put("RSAPrivateKey", privateKey);
        return keyMap;
    }
}
