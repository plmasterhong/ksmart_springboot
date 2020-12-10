package kr.or.ksmart37.ksmart_mybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		HttpSession session = request.getSession();
		
		String sessionId 	= (String) session.getAttribute("SID");
		String sessionLevel = (String) session.getAttribute("SLEVEL");
		String requestUri	= request.getRequestURI();
		
		//관리자 판매자 구매자
		
		if(sessionId == null) {
			if(requestUri.indexOf("/addMember") > -1) {
				return true;
			}else {
				response.sendRedirect("/login");
				return false;				
			}
		}else {
			if(sessionLevel != null && "2".equals(sessionLevel)){
				if(   requestUri.indexOf("/memberList") 	> -1	|| requestUri.indexOf("/addMember") 	> -1
				   || requestUri.indexOf("/sellerList") 	> -1 	|| requestUri.indexOf("/modifyMember") 	> -1
				   || requestUri.indexOf("/removeMember") 	> -1 ) {
					response.sendRedirect("/");
					return false;
				}
			}
			if(sessionLevel != null && "3".equals(sessionLevel)){
				if(   requestUri.indexOf("/memberList") 	> -1	|| requestUri.indexOf("/addMember") 	> -1
				   || requestUri.indexOf("/sellerList") 	> -1 	|| requestUri.indexOf("/modifyMember") 	> -1
				   || requestUri.indexOf("/removeMember") 	> -1 	|| requestUri.indexOf("/addGoods") 		> -1
				   || requestUri.indexOf("/modifyGoods") 	> -1 	|| requestUri.indexOf("/removeGoods") 	> -1) {
					response.sendRedirect("/");
					return false;
				}				
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
