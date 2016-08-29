package myenglish.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IpUtil {

	/**
	 * 获取客户端ip地址(可以穿透代理)
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getParameter("clientIp");
		if (StringUtils.isNotBlank(ip)) {
			return ip;
		}

		ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		// 处理获得的是由逗号分隔的由真实ip和代理ip组成的情况
		if (ip != null && ip.contains(",")) {
			ip = ip.substring(0, ip.indexOf(","));
		}

		return ip;
	}

}
