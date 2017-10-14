package com.sml.sz.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.service.SystemService;
import com.sml.sz.sys.utils.UserUtils;

/**
 * 当成功登录SSO系统时将会返回登录的userid根据此userid建立session会话;
 * @ClassName: SessionFilter 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author ray@zhou
 * @date 2014-1-23
 *
 */
public class SSO4InvokeContextFilter implements Filter{
	private WebApplicationContext applicationContext;
	
	public SSO4InvokeContextFilter() {
		super();
	}

	/**
	 * 过滤器注销时，触发此方法;
	 */
	public void destroy() {
		//暂时不做任何处理;
	}

	/**
	 * 根据用户id获取用户信息并且把用户信息放入session会话中;
	 * @Title: doFilter 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Params
	 * @throws
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		//从session中获取登陆用户;
		Object user = request.getSession().getAttribute("USER");
		if(user == null){
			//获取用户名;
			String userName = AssertionHolder.getAssertion().getPrincipal().getName();
			SystemService systemService = applicationContext.getBean(SystemService.class);
			User userObj = systemService.getUserByLoginName(userName);
			request.getSession().setAttribute("USER", userObj);
			String passWord = userObj.getPassword();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
			Subject subject  = SecurityUtils.getSubject();
			//校验;
			subject.login(token);
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * 初始化Spring上下文;
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		this.applicationContext = applicationContext;
	}
}
