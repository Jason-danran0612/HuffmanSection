package danran.test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressInUse {
public static void main(String[] args) {
		
		try {
			//根据域名查找主机的IP地址(由于可能有多个服务器，ip地址并不是唯一的，要想获取准确的，还需要获取所有的地址才行)
			InetAddress inetAddress=InetAddress.getByName("www.baidu.com");
			System.out.println(inetAddress);//结果：14.215.177.38
			//反向查找主机名
			InetAddress byName = InetAddress.getByName("113.105.245.103");
			System.out.println("反向查找主机名：   "+byName.getHostName());//如果没有主机名，会返回IP地址
			
			//得到主机的所有地址
			InetAddress[] inetAddresses=InetAddress.getAllByName("www.taobao.com");
			for (InetAddress address : inetAddresses) {
				System.out.println(address);
			}
			
			//getLocalHost获取当前主机名和IP地址
			InetAddress me = InetAddress.getLocalHost();//得到主机名/IP地址 的形式
			System.out.println(me);//如果电脑没有联网，会返回127.0.0.1
			System.out.println(me.getHostName());//得到主机名
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

