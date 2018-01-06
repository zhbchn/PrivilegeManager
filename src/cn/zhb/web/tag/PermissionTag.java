package cn.zhb.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.zhb.domain.Privilege;
import cn.zhb.domain.User;
import cn.zhb.service.SecurityService;

public class PermissionTag extends SimpleTagSupport {

	 private String value;

	    public void setValue(String value) {
	        this.value = value;
	    }

	    @Override
	    public void doTag() throws JspException, IOException {

	        // 判断用户拥有的权限值中，是否包含value
	        PageContext pageContext = (PageContext) this.getJspContext();
	        HttpSession session = pageContext.getSession();
	        User user = (User) session.getAttribute("user");

	        if (user != null) {
	            /*
	             * 得到用户的所有权限，如果你不想new出这个业务对象的话，也行，
	             * 这时，用户登录进来了你就要把用户的所有权限找出来，即用户对象里面要有一个集合维护他所有的权限，
	             * 但不想改动以前的设计了，我就调用service去完成，但这样做有点不太好，即Web层和业务逻辑层绑定在一起了。
	             */
	            SecurityService service = new SecurityService();
	            List<Privilege> privileges = service.getUserAllPrivilege(user.getId());
	            boolean b = false;
	            for (Privilege p : privileges) {
	                if (p.getName().equals(value)) {
	                    b = true;
	                    break;
	                }
	            }

	            if (b) {
	                this.getJspBody().invoke(null);
	            }
	        }

	    }
}
