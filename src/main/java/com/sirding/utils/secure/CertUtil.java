package com.sirding.utils.secure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * 证书工具类-包括整的导入导出、验证证书签名、验证证书的有效期
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class CertUtil {

	//证书库密码
	public static final String KEYSTROE_PASSWORD = "sirding";
	//证书库文件路径
	public static final String KEYSTROE_PATH = "C:/tomcat.keystore";
	
	//证书密码
	public static final String CER_PASSWORD = "sirding";
	//证书文件路径
	public static final String CERT_PATH = "C:/sirding.cert";
	//KeyStore.getDefaultType() 方法返回值也是 jks
	public static final String KEYSTORE_TYPE = "JKS";

	/**
	 * 向秘钥库中添加数字证书
	 * @param alias
	 */
	public static void addCertToStore(String alias,String cerName) throws Exception{
		FileInputStream in = null;
		FileInputStream certIn = null;
		FileOutputStream output = null;
		try {
			//加载秘钥库
			in=new FileInputStream(KEYSTROE_PATH);
			KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
			ks.load(in, KEYSTROE_PASSWORD.toCharArray());
			//加载要向秘钥库中添加的证书
			CertificateFactory cf=CertificateFactory.getInstance("X.509");
			String filePath = CERT_PATH + "/" + cerName;
			certIn = new FileInputStream(filePath);
			Certificate cert=cf.generateCertificate(certIn);
			certIn.close();
			//将数字证书导入到秘钥库中
			ks.setCertificateEntry(alias, cert);
			//重新存储秘钥库信息
			output=new FileOutputStream(KEYSTROE_PATH);
			ks.store(output, KEYSTROE_PASSWORD.toCharArray());
		}finally{
			closeIO(in,certIn,output);
		}
	}

	/**
	 * 将指定别名的数字证书从秘钥库中删除出去
	 * @param alias
	 */
	public static void delCertByAlias(String alias)  throws Exception{
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in=new FileInputStream(KEYSTROE_PATH);
			KeyStore ks=KeyStore.getInstance(KEYSTORE_TYPE);
			ks.load(in, KEYSTROE_PASSWORD.toCharArray());
			if(ks.containsAlias(alias)){
				ks.deleteEntry(alias);
			}
			out=new FileOutputStream(KEYSTROE_PATH);
			ks.store(out, KEYSTROE_PASSWORD.toCharArray());
		}finally{
			closeIO(in, null, out);
		}
	}

	/**
	 * 从指定的数字证书文件中获得证书的信息
	 * @return
	 */
	public static Certificate getCertInfoFromFile() throws Exception{
		CertificateFactory cf=CertificateFactory.getInstance("X.509");
		FileInputStream in=new FileInputStream(CERT_PATH);
		Certificate cert=cf.generateCertificate(in);
		in.close();
		return cert;
	}

	/**
	 * 从秘钥库中获得指定别名的数字证书的信息
	 * @param alias
	 */
	public static Certificate getCertInfoByAlias(String alias) throws Exception{
		FileInputStream in=new FileInputStream(KEYSTROE_PATH);
		KeyStore ks=KeyStore.getInstance(KEYSTORE_TYPE);
		ks.load(in, KEYSTROE_PASSWORD.toCharArray());
		//alias为条目的别名
		Certificate cert=ks.getCertificate(alias);
		in.close();
		return cert;
	}

	/**
	 * 验证证书的签名正确性
	 * @param alias
	 * @param cert
	 * @return
	 */
	public static boolean chkCertSign(String alias, Certificate cert){
		boolean flag = true;
		try {
			Certificate c = getCertInfoByAlias(alias);
			if(c == null){
				return false;
			}
			//获得CA公钥
			PublicKey pk = c.getPublicKey();
			cert.verify(pk);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证证书的有效时间
	 * @param cert
	 * @return
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	public static boolean chkCertDate(X509Certificate cert){
		boolean flag = true;
		try {
			cert.checkValidity(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 关闭指定的流对象
	 * @param fis
	 * @param fis1
	 * @param fos
	 * @throws Exception
	 */
	public static void closeIO(FileInputStream fis, FileInputStream fis1, FileOutputStream fos) throws Exception{
		if(fis != null){
			fis.close();
		}
		if(fis1 != null){
			fis1.close();
		}
		if(fos != null){
			fos.close();
		}
	}
}
