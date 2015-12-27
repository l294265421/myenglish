/**
 * 
 */
package myenglish.filter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mchange.v2.c3p0.impl.NewPooledConnection;

/**
 * @author yuncong
 * 
 */
public class LoggingFilter implements Filter {
	private PrintWriter logger;
	private String prefix;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		if (logger != null) {
			logger.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
		logger.println(new Date() + "	" + prefix
				+ httpServletRequest.getRequestURI());
		logger.flush();
		arg2.doFilter(httpServletRequest, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		prefix = arg0.getInitParameter("prefix");
		String logFileName = arg0.getInitParameter("logFileName");
		String appPath = arg0.getServletContext().getRealPath("/");
		System.out.println("appPath: " + appPath);
		try {
			logger = new PrintWriter(new File(appPath, logFileName));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
