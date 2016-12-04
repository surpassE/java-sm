package com.sirding.testutil;

import java.io.IOException;
import java.io.InputStream;
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
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class HttpClientUtilTest {

	/**
	 * POST方式发起http请求
	 */
	@Test
	@Ignore
	public void testTp(){
		String tpId = "tp_002_ybej";
//		String host1 = "http://101.201.152.200/openapi";
		String host1 = "http://192.168.1.249/openapi";
		String host2 = "https://51qiankundai.com/apptest";
		String host3 = "https://51qiankundai.com/openapi";
		//线上测试节点
		String host4 = "http://51qiankundai.com:9921/openapi";
		
		String uri1 = "/tpi/doRegist";
		String uri2 = "/tpi/realValidate";
		String uri3 = "/tpi/bindBankCard";
		
		String zhuce = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/gNaU+NqKEUlm05UZBB32kW4UuCzuZKY49SCwNIeR8XmTnvaSB9ks5Dw9tG0TEfN72929WPwzLFREFA00tnHWIESRvRZpDopcrW0c1MVEI6zU+6TtvF298NCEKIQyD9b4f6pyqPFA1byNFUYqBjCutuEysV616/EXPjoO5nfvz8wOfJgYMJg/LSXDNPHRb3ZZOnViF32fb9oz5f9S54Tt79gfj78Aitw39JiQ3/CQzxp3+EJlTKGArrvmkAGCxM3M7Se9AnJZlVw/TdmYyke0eiA+chppcQPE/YNwSvZvWv75AYeexoNTT";
		String shiming = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/gNaU+NqKEUlm05UZBB32kW4UuCzuZKY49SCwNIeR8XmTnvaSB9ks5Dw9tG0TEfN5gWBB+NYJTRxvUGc1kQq7KZgdQagkIaS8RHi6O1JAFzKL2M/vdHzi7CsIT5XEotA2VXDc70pog4WV5ZqXa//8CE48ninhQTirpJJllyZ88ywmXljCXfGcGCCiAQzKjj2RranPw/pSCiSvjXTAl4JBjiOs50dKGgJ7Tb6XEACnfiaOQPpB0K15iRh08dY/l+KUMtehxMr2HvjH5okYXl8oHxFeaotxIgN9HK+JQ2J/HMf0Fi4D2niSQ";
		String bindCard = "jiI2I7cP58PG1LVXWXnYDsE8Jwo46uAk/6Nu5+56cC/+7i7Qc9Vq+oqIED2Ty3+aikxlii7FVZxhdr1aPc5ev8/VgUMMB9crmXUagdnzaRDnlM94aNTmqaXqQ4GGerMQug+hlhol2qZ11YK5zqMIBHsGOEibJSK6aVZ+okTZ9D9oM3u0uGLm0OPy6YpbKgBACEtuRLMVLDlst3m3lIIa3pQ8hB8wpRq5mtCnd4MZibJ84G5ls8LDakhoKkea6YANfltG/yztdNmdg9nXkVQZwuCURgZ9tRa61DWYKwPhjiWxUE9vgxY0gEZVpvMKRmYDqWd2zizSZFKslaISkbxbn/DkrMUagXVd";
		
		//测试注册
//		this.requestByPost(host1 + uri1, tpId, zhuce);
//		this.requestByPost(host2 + uri1, tpId, zhuce);
//		this.requestByPost(host3 + uri1, tpId, zhuce);
//		this.requestByPost(host4 + uri1, tpId, zhuce);
		//测试实名
//		this.requestByPost(host1 + uri2, tpId, shiming);
		this.requestByPost(host2 + uri2, tpId, shiming);
//		this.requestByPost(host3 + uri2, tpId, shiming);
//		this.requestByPost(host4 + uri2, tpId, shiming);
		//测试绑卡
//		this.requestByPost(host1 + uri3, tpId, bindCard);
//		this.requestByPost(host2 + uri3, tpId, bindCard);
//		this.requestByPost(host3 + uri3, tpId, bindCard);
//		this.requestByPost(host4 + uri3, tpId, bindCard);
	}

	private CloseableHttpClient getHttpClient(){
		return HttpClients.createDefault();
	}

	private void closeHttpClient(CloseableHttpClient client) throws IOException{
		if (client != null){
			client.close();
		}
	}

	
	private void requestByPost(String url, String tpId, String msg){
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpPost post = new HttpPost(url);          //这里用上本机的某个工程做测试
			//创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("tpId", tpId));
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
