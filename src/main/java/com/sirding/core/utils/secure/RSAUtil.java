package com.sirding.core.utils.secure;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA 加密、解密、分块加密、分块解密、生成公钥、生成私钥
 * @author zc.ding
 * @date 2016年10月10日
 */
public class RSAUtil {

	//加密快的大小,值越大效率越低
	static final int KEY_SIZE = 1024;
	static final String KEY_ALGORTHM = "RSA";
	static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	//用于存储公钥、私钥的文件
	static final String KEY_STORE_PATH = "C:/pri_pub_key.gem";
	
	static final String DEFAULT_ENCODE = "UTF-8";

	//私钥
	static final String DEFAULT_PRI_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALKkyX47BqinI4qi6zjDKeCl0enva8WVQPenocNpWMxnE13oeAPD2biIyKjUhE/PCBZfETB/EvSaSSY5QWqd8YisFFpJBrDZOCUdgqeFZYwXdo8PXN4kDl+GPsCJKI5pZvLqs+Og3hwfxTgTh5ZjVNjLp0eGfsgMgX0wnFMFA84ZAgMBAAECgYB+0pnxMXpStQV4YJzZGURbpZzWlRBPntwWdT1T+y/9PJf1LRo2og2pAgJiSSz9c57sMuWDJlOQrw+LQU59oE9dZgz7OZ0SWzZFvFF+A69x3Q89ydxGKn8njwgV9LLEB/FMYCeEa4CqhhJMm5qqCOPWxjtxecwNFcDw53IPzROeuQJBANmbSJ90p9w8vEC0F2yZ/61brGNqcl1BdAt2WkBgx0Dlm1foNTb8FkUtCcmosGTKbIgBvT1J29CNfTzO8r4dPNsCQQDSKacc0eq++ovJsjaUNvSzySZMxJCPigL2YJ7o3w9HSe6i9kQU4J031Pdrj03bsJmGmPEHciLzPA3ncFNJBxkbAkBrk4/ofJRLlZ7/Yci+wLccbdigYUxee/AxhnYBo5Z9p8UPRVWhdChSVHylPAbQHR5gcnOqa+wGgxwpxqlMgUnHAkAHg3O1BRA2abCrslJfNCPFdbCH2BMu/okik8u6mZbrPopoixNeB7W3NEbwMnxfGU4z0K31TTBQr9nzZ0Gi/7z3AkEApWUJ3ofhJBazUJiVwyQZNZEfKUvPuLIGh0ht8A8IW3oY2O64CU7MQISaZkcOL7f8c/y6ESOssCBjk2bMdo71rQ==";
	//公钥
	static final String DEFAULT_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCypMl+OwaopyOKous4wyngpdHp72vFlUD3p6HDaVjMZxNd6HgDw9m4iMio1IRPzwgWXxEwfxL0mkkmOUFqnfGIrBRaSQaw2TglHYKnhWWMF3aPD1zeJA5fhj7AiSiOaWby6rPjoN4cH8U4E4eWY1TYy6dHhn7IDIF9MJxTBQPOGQIDAQAB";
	
	
	
	/**
	 * 使用默认的公钥串对对data进行加密操作
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception{
		return encrypt(data, DEFAULT_PUB_KEY);
	}
	
	/**
	 * 通过给定的公钥对data进行加密操作
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @param pubk
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String pubk) throws Exception{
		String result = null;
		Cipher cipher= null;
		try {
			cipher= Cipher.getInstance(KEY_ALGORTHM, new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubk));
			byte[] buf = cipher.doFinal(data.getBytes());
			byte[] buf2 = Base64.encodeBase64(buf);
			result = new String(buf2, DEFAULT_ENCODE);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}
	
	/**
	 * 使用默认的私钥对data数据进行分块加密
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptBlock(String data) throws Exception{
		return encryptBlock(data, DEFAULT_PUB_KEY);
	}
	
	/**
	 * 通过指定的私钥分块加密data数据
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @param pubk
	 * @return
	 * @throws Exception
	 */
	public static String encryptBlock(String data, String pubk) throws Exception{
		String result = null;
		byte[] buf = data.getBytes();
		int length = buf.length;
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORTHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubk));
			//加密块大小，值为127
			int blockSize = cipher.getBlockSize();
			//需要存储每个加密块空间的大小，值128
			int outputSize = cipher.getOutputSize(length);
			int blockCount = length % blockSize != 0 ? (length / blockSize + 1) : (length / blockSize);
			//创建用于存储加密后的字节数组
			byte[] outputBuf = new byte[outputSize * blockCount];
			int i = 0;
			while (length - i * blockSize > 0) {
				if (length - i * blockSize > blockSize)
					cipher.doFinal(buf, i * blockSize, blockSize, outputBuf, i * outputSize);
				else
					cipher.doFinal(buf, i * blockSize, length - i * blockSize, outputBuf, i * outputSize);
				i++;
			}
//			System.out.println("====加密后=Base64编码前===" + outputBuf.length);
			byte[] buf2 = Base64.encodeBase64(outputBuf);
//			System.out.println("====加密后=Base64编码后====" + buf2.length);
			result = new String(buf2, DEFAULT_ENCODE);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}
	
	/**
	 * 使用的默认的私钥对data进行RSA解密操作
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data) throws Exception{
		return decrypt(data, DEFAULT_PRI_KEY);
	}
	
	/**
	 * 用给定的私钥对data进行RSA解密
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @param prik
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, String prik) throws Exception{
		String result = null;
		Cipher cipher= null;
		try {
			cipher= Cipher.getInstance(KEY_ALGORTHM, new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(prik));
			byte[] buf = Base64.decodeBase64(data);
			byte[] buf2= cipher.doFinal(buf);
			result = new String(buf2, DEFAULT_ENCODE);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}
	
	/**
	 * 使用默认的私钥对data数据进行分块解密操作
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decryptBlock(String data) throws Exception{
		return decryptBlock(data, DEFAULT_PRI_KEY);
	}
	
	/**
	 * 通过给定的私钥对data数据分块解密
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param data
	 * @param prik
	 * @return
	 * @throws Exception
	 */
	public static String decryptBlock(String data, String prik) throws Exception{
		String result = null;
//		System.out.println("====解密前=Base64解码前====" + data.length());
		byte[] buf = Base64.decodeBase64(data.getBytes(DEFAULT_ENCODE));
//		System.out.println("====解密前=Base64解码后====" + buf.length);
		int length = buf.length;
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORTHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(prik));
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int i = 0;
			while (length - i * blockSize > 0) {
				bos.write(cipher.doFinal(buf, i * blockSize, blockSize));
				i++;
			}
			byte[] buf2 = bos.toByteArray();
			result = new String(buf2, DEFAULT_ENCODE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return result;
	}


	/**
	 * 生成密钥对
	 * @return KeyPair
	 * @throws EncryptException
	 */
	private static KeyPair generateKeyPair() throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORTHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			//将生成的密钥对保存的到文件中
//			saveKeyPair(keyPair, null);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 从指定文件中读取密钥对
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param keyStortPath
	 * @return
	 * @throws Exception
	 */
	public static KeyPair getKeyPair(String keyStorePath) throws Exception {
		if(keyStorePath == null || keyStorePath.length() <= 0){
			keyStorePath = KEY_STORE_PATH;
		}
		FileInputStream fis = new FileInputStream(keyStorePath);
		ObjectInputStream oos = new ObjectInputStream(fis);
		KeyPair kp = (KeyPair) oos.readObject();
		oos.close();
		fis.close();
		return kp;
	}

	/**
	 * 将密钥对保存指定的文件中
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param kp
	 * @param keyStorePath
	 * @throws Exception
	 */
	@Deprecated
	public static void saveKeyPair(KeyPair kp, String keyStorePath) throws Exception {
		if(keyStorePath == null || keyStorePath.length() <= 0){
			keyStorePath = KEY_STORE_PATH;
		}
		FileOutputStream fos = new FileOutputStream(keyStorePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 生成密钥
		oos.writeObject(kp);
		oos.close();
		fos.close();
	}

	/**
	 * 通过指定的参数生成公钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static String getPubk(byte[] modulus, byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance(KEY_ALGORTHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger( modulus), new BigInteger(publicExponent));
			RSAPublicKey pubk =  (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
			return Base64.encodeBase64String(pubk.getEncoded());
		} catch (Exception ex) {
			throw new Exception("无法生成公钥"); 
		}
	}
	
	/**
	 * 通过指定的参数生成私钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws Exception
	 */
	public static String getPrik(byte[] modulus, byte[] privateExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance(KEY_ALGORTHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
			RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger( modulus), new BigInteger(privateExponent));
			RSAPrivateKey prik = (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
			return Base64.encodeBase64String(prik.getEncoded());
		} catch (Exception e) {
			throw new Exception("无法生成私钥"); 
		}
	}

	
	/**
	 * 将pubk转为公钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param pubk
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String pubk) throws Exception{
		try {
			byte[] buffer= Base64.decodeBase64(pubk);
			KeyFactory keyFactory= KeyFactory.getInstance(KEY_ALGORTHM);
			X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new Exception("公钥数据为空");
		}
	}
	
	/**
	 * 将prik转为私钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @param prik
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey getPrivateKey(String prik) throws Exception{
		try {
			byte[] buffer= Base64.decodeBase64(prik);
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory= KeyFactory.getInstance(KEY_ALGORTHM);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new Exception("私钥数据为空");
		}
	}


	/**  
	 * 16进制 To byte[] 解决RSA编码错误：Bad arguments 
	 * @param hexString  
	 * @return byte[]  
	 */  
	@Deprecated
	static byte[] hexStringToBytes(String hexString) {  
		if (hexString == null || hexString.equals("")) {  
			return null;  
		}  
		hexString = hexString.toUpperCase();  
		int length = hexString.length() / 2;  
		char[] hexChars = hexString.toCharArray();  
		byte[] d = new byte[length];  
		for (int i = 0; i < length; i++) {  
			int pos = i * 2;  
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
		}  
		return d;  
	}

	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	private static byte charToByte(char c) {  
		return (byte)"0123456789ABCDEF".indexOf(c);  
	}
	
	/**
	 * 生成Base64处理过的公钥、私钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @throws Exception
	 */
	public static void genPrikAndPubk() throws Exception{
		KeyPair keyPair = generateKeyPair();
		System.out.println("======原始私钥====");
		System.out.println(keyPair.getPrivate());
		System.out.println("======Base64编码后的私钥==");
		byte[] buf = Base64.encodeBase64(keyPair.getPrivate().getEncoded());
		System.out.println(new String(buf, DEFAULT_ENCODE));

		System.out.println("======原始公钥=====");
		System.out.println(keyPair.getPublic());
		System.out.println("======Base64编码后的公钥==");
		byte[] buf2 = Base64.encodeBase64(keyPair.getPublic().getEncoded());
		System.out.println(new String(buf2,DEFAULT_ENCODE));
	}
	
	/**
	 * 生成自定义公钥私钥
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @throws Exception
	 */
	@Deprecated
	public static void genComPrikAndPubk() throws Exception {
		String modulusMsg = "51qiankundai.com";
		byte[] modulus = modulusMsg.getBytes();
		String exponentMsg = "51qiankundai.com";
		byte[] exponent = exponentMsg.getBytes();
		String pubk = getPubk(modulus, exponent);
		System.out.println("=====生成的公钥=========");
		System.out.println(pubk);
		System.out.println("=====生成的私钥=========");
		String prik = getPrik(modulus, exponent);
		System.out.println(prik);
	}
	
	/**
	 * @Described			: 使用默认的公钥对数据进行签名算法
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String sign(String data) throws Exception{
		return sign(data, DEFAULT_PUB_KEY);
	}
	
	/**
	 * @Described			: 对数据进行签名算法
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(String data, String publicKey) throws Exception{
		return sign(data.getBytes(DEFAULT_ENCODE), publicKey);
	}
	
	
	/**
	 * @Described			: 对数据进行签名算法
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String publicKey) throws Exception{
		//解密公钥
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		//构造X509EncodedKeySpec对象
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		//指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		//取公钥匙对象
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey2);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}
	
	/**
	 * @Described			: 使用默认私钥验证签名
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String data, String sign)throws Exception{
		return verify(data, DEFAULT_PRI_KEY, sign);
	}
	
	/**
	 * @Described			: 验证数字签名
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @param privateKey
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String data, String privateKey, String sign)throws Exception{
		return verify(data.getBytes(DEFAULT_ENCODE), privateKey, sign);
	}
	
	/**
	 * @Described			: 验证数字签名
	 * @author				: zc.ding
	 * @date 				: 2016年11月30日
	 * @param data
	 * @param privateKey
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String privateKey, String sign)throws Exception{
		//解密公钥
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		//构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		//指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		//取私钥匙对象
		PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		//用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey2);
		signature.update(data);
		//验证签名是否正常
		return signature.verify(Base64.decodeBase64(sign));
	}


	/**
	 * 测试简单数据
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @throws Exception
	 */
	static void testSimpleData() throws Exception{
		String data = "hello world";
		System.out.println("=========原数据===========");
		System.out.println(data);
		String msg = encrypt(data);
		System.out.println("=========加密后===========");
		System.out.println(msg);
		System.out.println("=========解密后===========");
		msg = decrypt(msg);
		System.out.println(msg);
		System.out.println("对比加解密后的数据：" + msg.equals(data));
	}
	
	/**
	 * 
	 * @date 2016年10月10日
	 * @author zc.ding
	 * @throws Exception
	 */
	static void testBigData() throws Exception{
		String data = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk9/zSO5lTV5fVUclBbWZLDITmg9DnOeJUylDvjdZ8ohKGYGagEai1TICsN2lSYaIhO+a8bu7vbwISOm5fx+GKflxc5+d1KCLKmd+nl4Dfc5YDyOro0CMgVv0ATR4fraMfYfY731yrraORPcBMy8bZUDUE60AGNJRg/cVNzFGnrzZ2l7I4k1F8YuCj/FwwOduFXvnYDi7cGN3xSGNMdC2b1q0S3521W8hTSQwBzfPBuyEhTZ7dyvcdERzw94a56EMupmArgKTQehkRqe5QOxE29Xm0Nm5rfz4r7rGPR43YzXhrkEdHj0PbcVi2LbwfoXKa";
		System.out.println("=========原数据===========");
		System.out.println(data);
		System.out.println("原数据长度：" + data.length() + "\t" + data.getBytes().length);
		System.out.println("=========加密后===========");
		String msg = encryptBlock(data);
		System.out.println(msg);
		System.out.println("=========解密后===========");
		msg = decryptBlock(msg);
		System.out.println(msg);
		System.out.println("对比加解密后的数据：" + msg.equals(data));
	}
	
	
	public static void main(String[] args) throws Exception {
		//生成秘钥库文件
//		RSAUtil.generateKeyPair();
		//生成私钥、公钥
//		genPrikAndPubk();
		//测试简单数据的加密解密
		testSimpleData();
		//测试大数据的机密解密
//		testBigData();
		//生成自定义私钥、公钥
//		genComPrikAndPubk();
	}
}
