package cn.zhb.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.Resource;
import cn.zhb.service.SecurityService;
import cn.zhb.utils.WebUtils;

/**
 * Servlet implementation class ResourceServlet
 */
public class ResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecurityService service = new SecurityService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        if ("forUpdatePrivilegeUI".equals(method)) {
            forUpdatePrivilegeUI(request, response);
        }
        
        if ("updatePrivilege".equals(method)) {
            updatePrivilege(request, response);
        }
	}

	//更新资源的权限
	private void updatePrivilege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            String resourceid = request.getParameter("rid");
	            String privilegeid = request.getParameter("pid");
	            service.updateResourcePrivilege(resourceid, privilegeid);

	            request.setAttribute("message", "更新成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "更新失败！！！");
	        }
	        request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	// 为更新资源权限提供UI界面
	private void forUpdatePrivilegeUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String resourceid = request.getParameter("id");
	        Resource r = service.finfResourceByID(resourceid);

	        // 得到系统中的所有权限
	        List<Privilege> list = service.getAllPrivilege();

	        request.setAttribute("resource", r);
	        request.setAttribute("list", list);

	        request.getRequestDispatcher("/security/updateResourcePrivilege.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
	            Resource r = WebUtils.request2Bean(request, Resource.class);
//	            r.setId(UUID.randomUUID().toString());
	            service.addResource(r);

	            request.setAttribute("message", "添加成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "添加失败！！！");
	        }
	        request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/security/addresource.jsp").forward(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Resource> list = service.getAllResource();
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("/security/listresource.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
