package cn.zhb.web.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhb.domain.Role;
import cn.zhb.domain.User;
import cn.zhb.service.SecurityService;
import cn.zhb.utils.WebUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecurityService service = new SecurityService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		
		if ("addUI".equals(method)) {
            addUI(request, response);
        }
		if ("add".equals(method)) {
            add(request, response);
        }
		if ("forUpdateUserRoleUI".equals(method)) {
            forUpdateUserRoleUI(request, response);
		}
		if ("updateRole".equals(method)) {
            updateRole(request, response);
        }
		
		 if ("login".equals(method)) {
	            login(request, response);
		 }
		 if ("logout".equals(method)) {
	            logout(request, response);
	        }
	        
        
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("user");
        response.sendRedirect("/PrivilegeManager/login.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = service.findUser(username, password);
        if (user == null) {
            request.setAttribute("message", "用户名或密码错误！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect("/PrivilegeManager/admin/admin.jsp");
	}

	private void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            String userid = request.getParameter("userid");
            String[] rids = request.getParameterValues("rid");
            service.updateUserRole(userid, rids);

            request.setAttribute("message", "更新成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "更新失败！！！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	private void forUpdateUserRoleUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("id");
        User user = service.findUser(userid);

        List<Role> list = service.getAllRole();
        request.setAttribute("user", user);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/updateUserRole.jsp").forward(request, response);

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            User user = WebUtils.request2Bean(request, User.class);
//            user.setId(UUID.randomUUID().toString());
            service.addUser(user);

            request.setAttribute("message", "添加成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加失败！！！");
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/security/adduser.jsp").forward(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> list = service.getAllUser();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/security/listuser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
