package cn.zhb.domain;

import java.util.HashSet;
import java.util.Set;

public class User extends Generic {
//	 private String id;
	    private String username;
	    private String password;
	    private String description;

	    private Set<Role> roles = new HashSet<Role>();

//	    public String getId() {
//	        return id;
//	    }
//
//	    public void setId(String id) {
//	        this.id = id;
//	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
}
