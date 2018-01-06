package cn.zhb.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhb.domain.Privilege;
import cn.zhb.service.SecurityService;
import cn.zhb.utils.WebUtils;

/**
 * Servlet implementation class PrivilegeServlet
 */
public class PrivilegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SecurityService service = new SecurityService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrivilegeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
        // 请求派发
        if ("getAll".equals(method)) {
            getAll(request, response);
        }
        if ("addUI".equals(method)) {
            addUI(request, response);
        }
        
        if ("add".equals(method)) {
            add(request, response);
        }
    }

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            Privilege p = WebUtils.request2Bean(request, Privilege.class);
//	            p.setId(UUID.randomUUID().toString());
	            service.addPrivilege(p);

	            request.setAttribute("message", "添加成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "添加失败！！！");
	        }
	        request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/security/addprivilege.jsp").forward(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Privilege> list = service.getAllPrivilege();
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("/security/listprivilege.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
