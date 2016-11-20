package com.sirding.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @Described	: 用于生成注册码、序列号
 * @project		: com.sirding.core.utils.LicenseUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月17日
 */
public class LicenseUtil {
	
	/**
	 * @Described	: 获得指定地址的mac信息	
	 * 				  InetAddress inetAddress = InetAddress.getLocalHost();//本地mac信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: String
	 * @param inetAddress
	 * @return
	 * @throws Exception
	 */
    public static String getMac(InetAddress inetAddress)throws Exception{ 
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();  
        StringBuffer sb = new StringBuffer();  
        for(int i=0;i<mac.length;i++){  
            if(i!=0){  
                sb.append("-");  
            }  
            //mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length()==1?0+s:s);  
        }  
        return sb.toString().toUpperCase();  
    }  
}
