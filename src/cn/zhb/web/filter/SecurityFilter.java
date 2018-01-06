package cn.zhb.web.filter;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.Resource;
import cn.zhb.domain.User;
import cn.zhb.service.SecurityService;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {
       
    /**
     * @see Filter#Filter()
     */
    public SecurityFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 1. 检查用户是否已登录
        User user = (User) request.getSession().getAttribute("user");

        // 2. 没登录，登录去
        if (user == null) {
            request.setAttribute("message", "请先登录！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        // 3. 得到用户想访问的资源
        String uri = request.getRequestURI();

        // 4. 得到访问该资源需要的权限
        SecurityService service = new SecurityService();
        Resource r = service.findResource(uri);
        /*
         * 你要访问的资源，我在权限管理系统里面没有说访问这个资源需要权限，
         * 也即这个资源不需要被权限系统控制，只有被权限系统控制的资源，在数据库里面
         * 才有，如果为null，这个资源不受权限系统控制。
         */
        if (r == null) {
            chain.doFilter(request, response);
            return;
        }
        Privilege required_Privilege = r.getPrivilege(); // 得到访问资源需要的权限

        // 5. 判断用户是否有相应权限
        List<Privilege> list = service.getUserAllPrivilege(user.getId()); // 得到用户所有权限
        if (!list.contains(required_Privilege)) {
            // 6. 没有权限，则提示用户权限不足，联系管理员
            request.setAttribute("message", "对不起，您没有权限，请联系管理员！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        // 7. 如果有，则则放行
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
