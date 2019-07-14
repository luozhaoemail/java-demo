package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

class LoginInterceptor implements HandlerInterceptor {
	
	/**在进入控制器(controller)之前执行
	 * 如果返回值为 false,阻止进入控制器,返回true则允许进入控制器
	 * 控制代码 
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,Object obj) throws Exception {
		System.out.println("第一步拦截preHandle");
		String uri = req.getRequestURI();
		if(uri.equals("/springMVC01/login") || uri.equals("/springMVC01/register"))//放行login控制器
			return true;
		else
		{
			//获取会话缓存
			Object oj = req.getSession().getAttribute("use");
			if(oj!=null)
				return true; //说明已经登陆过了
			
			//差不多session则返回去登陆！
			res.sendRedirect("/springMVC01/login.jsp");
			return false;
		}
	}

	/**控制器controller执行完成后,进入到 jsp 之前执行.
	 * 日志记录，敏感词语过滤
	 */
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,Object obj, ModelAndView mv) throws Exception {
		System.out.println("第二步拦截postHandle");
		System.out.println("\t往"+mv.getViewName()+"跳转！");		
	}
	
	/**JSP执行完成之后执行
	 * 记录执行过程中出现的异常.
	 * 可以把异常记录到日志中
	 */
	@Override
	public void afterCompletion(HttpServletRequest req,HttpServletResponse res, Object obj, Exception e)throws Exception {
		System.out.println("第三步拦截afterCompletion");
		//如果没有异常，e=null
		System.out.println("\t afterCompletion="+e);
		
	}

}
