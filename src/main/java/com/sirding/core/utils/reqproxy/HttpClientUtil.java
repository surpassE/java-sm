package com.sirding.core.utils.reqproxy;

import java.security.KeyStore;

import org.apache.log4j.Logger;

import com.sirding.core.utils.secure.DesUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;


import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

/**
 * 基于HttpClient实现服务端模拟请求http、https
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class HttpClientUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 通过Httpclient模拟https请求，需要加载请求认证证书
	 * @param url	请求地址
	 * @param data	请求数据，data的类型String|Map<String, String>|
	 * 					String:建议JSON格式的数据，接口中会对data进行DES加密，默认请求的属性名称reqProxyData  eg reqProxyData=data
	 * 					Map<String, String> 请求的参数列表,接口中不会其进行加密操作
	 * 					List<NameValuePair> 请求参数有调用者自行封装
	 * @param flag 	0: 不接收响应信息，1：接收响应信息
	 * @param keyStorePath	证书库文件路径
	 * @param keyStorePwd	证书密码
	 * @param trustStorePath	信任证书库路径，可能同keyStorePath是一个
	 * @param trustStorePwd		信任证书库密码
	 * @return
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	public static String reqAndResMsg(
			String url, 
			Object data, 
			String flag,
			String keyStorePath, 
			String keyStorePwd, 
			String trustStorePath, 
			String trustStorePwd){
		try {
			HttpClient httpClient = buildHttpClient(keyStorePath, keyStorePwd, trustStorePath, trustStorePwd);
			return reqAndResMsg(url, data, flag, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过Httpclient模拟http获得https(不需要加载证书相关信息)
	 * @param url 请求的地址
	 * @param data	请求数据，data的类型String|Map<String, String>|List<NameValuePair><br/>
	 * 					String:建议JSON格式的数据，接口中会对data进行DES加密，默认请求的属性名称reqProxyData  eg reqProxyData=data<br/>
	 * 					Map<String, String> 请求的参数列表,接口中不会其进行加密操作<br/>
	 * 					List<NameValuePair> 请求参数有调用者自行封装<br/>
	 * @param flag 0: 不接收响应信息，1：接收响应信息
	 * @return
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	public static String reqAndResMsg(String url, Object data, String flag){
			HttpClient httpClient = HttpClients.createDefault();
			return reqAndResMsg(url, data, flag, httpClient);
	}

	/**
	 * 通过HttpClient模拟请求
	 * @param url
	 * @param data
	 * @param flag 0: 不接收响应信息，1：接收响应信息
	 * @param httpClient 使用代理请求的类型  http https
	 * @return
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	private static String reqAndResMsg(String url, Object data, String flag, HttpClient httpClient){
		String content = "";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			logger.debug("====请求的地址:" + url);
			//设置请求的连接配置
			httpPost.setConfig(getRequestConfig(10000, true));
			// 如果参数是中文，需要进行转码
			httpPost.setEntity(new UrlEncodedFormEntity(getReqParams(data), Consts.UTF_8));  
			//执行请求并获得请求的响应
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.debug(httpStatus);
			if(HttpStatus.SC_OK == httpStatus && respEntity != null){
				content = receivedMsg(respEntity.getContent(), "UTF-8", flag);
				logger.debug("Response content: " + content);  
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			content = "error";
			e.printStackTrace();
		} finally{
			if(httpPost != null){
				httpPost.releaseConnection();
			}
		}
		return content;
	}
	
	/**
	 * 封装请求的参数
	 * @param data
	 * @return
	 * @throws Exception
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	@SuppressWarnings("unchecked")
	private static List<NameValuePair> getReqParams(Object data) throws Exception{
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if(data instanceof String){
			logger.debug("====加密前数据信息:" + data);
			//这里对数据进行加密操作
			String param = DesUtil.encrypt((String)data);
			list.add(new BasicNameValuePair("reqProxyData", param));
			logger.debug("====加密后的数据:" + data);
		}else if(data instanceof Map){
			Map<String, String> map = (Map<String, String>)data;
			if(map != null){
				for(String key : map.keySet()){
					list.add(new BasicNameValuePair(key, map.get(key)));
				}
			}
		}else if(data instanceof List){
			return (List<NameValuePair>)data;
		}
		return list;
	}


	/**
	 * 创建httpClient绑定证书，用于执行https请求
	 * @param keyStorePath 证书库的路径
	 * @param keyStorePwd 证书密码
	 * @param trustStorePath 信任证书库路径
	 * @param trustStorePwd 信任证书库密码
	 * @return
	 * @throws Exception
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	public static HttpClient buildHttpClient(String keyStorePath, 
			String keyStorePwd, 
			String trustStorePath, 
			String trustStorePwd) throws Exception{
		//加载证书库
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream in = new FileInputStream(keyStorePath);
		keyStore.load(in, keyStorePwd.toCharArray());
		in.close();
		//加载信任库
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		in = new FileInputStream(trustStorePath);
		trustStore.load(in, trustStorePwd.toCharArray());
		in.close();
		/* 服务端tomcat 节点中clientAuth="false"时使用如下方式进行访问
		SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(keyStore, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {//信任所有
                return true;
            }
        }).build();
		 */
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keyStore);

		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(keyStore, "password".toCharArray());

		SSLContext sslContext = SSLContext.getInstance("TLS");
		//双向认证，服务端tomcat 节点中clientAuth="true"，如果为false那么可以使用sslContext.init(null, tmf.getTrustManagers(), null);进行配置
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		//(X509HostnameVerifier) SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER 不验证证书中的主机ip是否和keystore中的主机ip一致
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, (X509HostnameVerifier) SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		RegistryBuilder<ConnectionSocketFactory> rb = RegistryBuilder.create();
		rb.register("https", csf);
		org.apache.http.config.Registry<ConnectionSocketFactory> reg = rb.build();
		PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager(reg);
		//是否配置连接池进行管理
		//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpClient httpClient = HttpClients.custom().setConnectionManager(pccm).build();
		return httpClient;
	}


	/**
	 * 设置请求的参数
	 * @param httpPost
	 * @param time
	 * @param flag
	 * @return
	 */
	private static RequestConfig getRequestConfig(int time, boolean flag){
		Builder build = RequestConfig.custom();
		build.setConnectTimeout(time);
		build.setSocketTimeout(time);
		build.setConnectionRequestTimeout(time);
		build.setExpectContinueEnabled(flag);
		return build.build();
	}

	/**
	 * 设置请求头部信息
	 * @param httpPost
	 */
	void setHeadInfo(HttpPost httpPost){
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "en-US,en;q=0.5");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader( "User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:28.0) Gecko/20100101 Firefox/28.0");
	}
	
	/**
	 * 接收请求响应信息
	 * @param is
	 * @param character
	 * @param flag
	 * @return
	 * @throws Exception
	 * @author zc.ding
	 * @date 2016年10月16日
	 */
	private static String receivedMsg(InputStream is, String character, String flag) throws Exception{
		String content = "";
		if(flag != null && "1".equals(flag)){
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, character));
			String line;
			while((line = reader.readLine()) != null) {
				content+=line;
			}
			//回来的数据格式是JSON格式的字符串，去掉首尾的双引号
			//返回的数据是否需要解密
//			content = DesUtil.decrypt(content.substring(1, content.length() - 1));
		}
		return content;
	}

}


/**
 * 创建httpClient 无认证连接代理、单向认证代理、双向认证代理
 * @param type 1：单向认证连接	2：双向认证连接
 * @return
 * @throws Exception
 */
/**
此种证书绑定方式在v4.0(4.3)中已经被弃用
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
public static HttpClient buildHttpClient(String type) throws Exception{
	HttpClient httpClient = null;
	if(type == null || type == ""){
		httpClient = HttpClients.createDefault();
	}else{
		//加载证书库
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream in = new FileInputStream(KEYSTORE_PATH);
		keyStore.load(in, KEYSTORE_PASSWORD.toCharArray());
		in.close();
		SSLSocketFactory socketFactory = null;
		if("1".equals(type)){						//单向认证策略
			socketFactory = new SSLSocketFactory(
					SSLSocketFactory.TLS,
					null,
					KEYSTORE_PASSWORD,
					keyStore,
					null,
					null,
					(X509HostnameVerifier) SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		}else if("2".endsWith(type)){					//双向认证策略
			//加载信任库
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			in = new FileInputStream(TRUSTSTORE_PATH);
			trustStore.load(in, TRUSTSTORE_PASSWORD.toCharArray());
			in.close();
			socketFactory = new SSLSocketFactory(
					SSLSocketFactory.TLS,
					keyStore,
					KEYSTORE_PASSWORD,
					trustStore,
					null,
					null,
					(X509HostnameVerifier) SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		}
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("https", 8443, socketFactory));
		ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
		httpClient = new DefaultHttpClient(mgr);
	}
	return httpClient;
}
 */
