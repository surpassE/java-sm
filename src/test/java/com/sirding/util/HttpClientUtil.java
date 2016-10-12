package com.sirding.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientUtil {

	/**
	 * 通过GET方式发起http请求
	 */
	@Test
	public void requestByGetMethod(){
		//创建默认的httpClient实例
		CloseableHttpClient httpClient = getHttpClient();
		try {
			//用get方法发送http请求
			HttpGet get = new HttpGet("http://www.baidu.com");
			System.out.println("执行get请求:...."+get.getURI());
			CloseableHttpResponse httpResponse = null;
			//发送get请求
			httpResponse = httpClient.execute(get);
			try{
				//response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity){
					System.out.println("响应状态码:"+ httpResponse.getStatusLine());
					System.out.println("-------------------------------------------------");
					System.out.println("响应内容:" + EntityUtils.toString(entity));
					System.out.println("-------------------------------------------------");                    
				}
			}
			finally{
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				closeHttpClient(httpClient);
			} catch (IOException e){
				e.printStackTrace();
			}
		}

	}


	/**
	 * POST方式发起http请求
	 */
	@Test
	public void testTp(){
		String host1 = "http://101.201.152.200/openapi";
		String host2 = "https://51qiankundai.com/apptest";
		String host3 = "https://51qiankundai.com/openapi";
		//线上测试节点
		String host4 = "http://51qiankundai.com:9921/openapi";
		
		String uri1 = "/tp_mfq/doRegist";
		String uri2 = "/tp_mfq/realValidate";
		String uri3 = "/tp_mfq/bindBankCard";
		
		String zhuce = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/lOhDzov/CBR7RqNDMgVCoM1tQsvWPo4Utd9vhJFiiAk3AB1Bp8gsuZzQuNirx/D3dBBq8rhF34F7hj2rMpheZIAnDeDeupDqhmTXl3+p7i876Wt53eyzicOGMQVjE5LOq1UJkdYYRONaaggnkCjm0zmU8YnU1kZRWWgFeSlXKF3e/nk2P9z/CamA8Arg+XRn93dRPdF++13+Mxb633fFh9FVLvFGFQbaZ2/w0Wisyg0MZa6hBvz3Rejq8UKiiGxzWW0M4A2w5+soVxy8B6y3bTOjyhHGBjSw58YLxfqyoOvF8Qxv+k1hR";
		String shiming = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/+7i7Qc9Vq+oqIED2Ty3+aikxlii7FVZxhdr1aPc5ev8/VgUMMB9crmXUagdnzaRDnlM94aNTmqaXqQ4GGerMQug+hlhol2qZ11YK5zqMIBHsGOEibJSK6aVZ+okTZ9D9oM3u0uGLm0OPy6YpbKgBACEtuRLMVLDlst3m3lIIa3pQ8hB8wpRq5mtCnd4MZibJ84G5ls8LDakhoKkea6YANfltG/yztdNmdg9nXkVQZwuCURgZ9tRa61DWYKwPhjiWxUE9vgxY0gEZVpvMKRmYDqWd2zizSZFKslaISkbxbn/DkrMUagXVd";
		String bindCard = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/+7i7Qc9Vq+oqIED2Ty3+aikxlii7FVZxhdr1aPc5ev8/VgUMMB9crmXUagdnzaRDnlM94aNTmqaXqQ4GGerMQug+hlhol2qZ11YK5zqMIBHsGOEibJSK6aVZ+okTZ9D9oM3u0uGLm0OPy6YpbKgBACEtuRLMVLDlst3m3lIIa3pQ8hB8wpRq5mtCnd4MZibJ84G5ls8LDakhoKkea6YANfltG/yztdNmdg9nXkVQZwuCURgZ9tRa61DWYKwPhjiWxUE9vgxY0gEZVpvMKRmYDqWd2zizSZFKslaISkbxbn/DkrMUagXVd";
		//测试注册
//		this.requestByPost(host1 + uri1, zhuce);
//		this.requestByPost(host2 + uri1, zhuce);
		this.requestByPost(host3 + uri1, zhuce);
//		this.requestByPost(host4 + uri1, zhuce);
		//测试实名
//		this.requestByPost(host1 + uri2, shiming);
//		this.requestByPost(host2 + uri2, shiming);
//		this.requestByPost(host3 + uri2, shiming);
//		this.requestByPost(host4 + uri2, shiming);
		//测试绑卡
//		this.requestByPost(host1 + uri3, bindCard);
//		this.requestByPost(host2 + uri3, bindCard);
//		this.requestByPost(host3 + uri3, bindCard);
//		this.requestByPost(host4 + uri3, bindCard);
		
	}

	private CloseableHttpClient getHttpClient(){
		return HttpClients.createDefault();
	}

	private void closeHttpClient(CloseableHttpClient client) throws IOException{
		if (client != null){
			client.close();
		}
	}

	
	private void requestByPost(String url, String msg){
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpPost post = new HttpPost(url);          //这里用上本机的某个工程做测试
			//创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("tpId", "tp_001_mfq"));
			list.add(new BasicNameValuePair("msg", msg));
			//url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
			post.setEntity(uefEntity);
			System.out.println("POST 请求...." + post.getURI());
			//执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try{
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity){
					System.out.println("---------------请求的数据----------------------------------------");
					System.out.println(EntityUtils.toString(uefEntity));
					System.out.println("-------------------------------------------------------");
					byte[] buf = new byte[1024];
					InputStream is = entity.getContent();
					int length = 0;
					System.out.println("----------------响应的结果---------------------------------------");
					while((length = is.read(buf)) > 0){
						System.out.println(new String(buf, 0, length));
					}
					System.out.println("-------------------------------------------------------");
				}
			} finally{
				httpResponse.close();
			}
		} catch( UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				closeHttpClient(httpClient);                
			} catch(Exception e){
				e.printStackTrace();
			}
		}

	}
	
}
