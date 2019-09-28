package team.AI.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingUtils {

	public static Domain ping(String domian) {
		long start = System.currentTimeMillis();
		Domain result = new Domain();
		try {
			InetAddress address = InetAddress.getByName(domian);

			result.ip = address.getHostAddress();
			result.host = address.getHostName();
			long end = System.currentTimeMillis();
			result.time = (end - start);
			result.bool=true;
		} catch (UnknownHostException e) {
			result.ip = "0.0.0.0";
			result.host = "UNKONW";
			result.bool=false;
		}
		return result;
	}

	public static class Domain {
		String ip;
		String host;
		long time;
		boolean bool;

		@Override
		public String toString() {

			return String.format("host=%s, ip=%s, time=%s", host, ip, time);
		}
	}
	
	public static void main(String[] args) {
		Domain domain = ping("www.fangongheike.com");
		System.out.println(domain.bool);
	}
}
