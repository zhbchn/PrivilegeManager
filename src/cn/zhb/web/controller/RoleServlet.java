package cn.zhb.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.Role;
import cn.zhb.service.SecurityService;
import cn.zhb.utils.WebUtils;

/**
 * Servlet implementation class RoleServlet
 */
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private SecurityService service = new SecurityService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
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
        if ("forUpdateRolePrivilegeUI".equals(method)) {
            forUpdateRolePrivilegeUI(request, response);
        }

        if ("updatePrivilege".equals(method)) {
            updatePrivilege(request, response);
        }
	}

	private void updatePrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            String roleid = request.getParameter("roleid");
	            String[] pids = request.getParameterValues("pid");
	            service.updateRolePrivilege(roleid, pids);

	            request.setAttribute("message", "更新成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "更新失败！！！");
	        }
	        request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	private void forUpdateRolePrivilegeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String roleid = request.getParameter("id");
	        Role r = service.findRole(roleid);

	        List<Privilege> list = service.getAllPrivilege();
	        request.setAttribute("role", r);
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("/security/updateRolePrivilege.jsp").forward(request, response);

	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            Role role = WebUtils.request2Bean(request, Role.class);
//	            role.setId(UUID.randomUUID().toString()); // 还可编写一个Generic类
	            service.addRole(role);

	            request.setAttribute("message", "添加成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "添加失败！！！");
	        }
	        request.getRequestDispatcher("/message.jsp").forward(request, response);

	}

	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.getRequestDispatcher("/security/addrole.jsp").forward(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Role> list = service.getAllRole();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/security/listrole.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
