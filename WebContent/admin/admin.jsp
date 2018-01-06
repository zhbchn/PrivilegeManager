<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/zhb" prefix="zhb" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>权限管理</title>
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="./build/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="./build/css/app.css" media="all">
    <link rel="stylesheet" href="./build/css/themes/blue.css" media="all">
</head>

<body class="kit-theme">
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">权限管理</div>
            <div class="layui-logo kit-logo-mobile">K</div>
            <ul class="layui-nav layui-layout-left kit-nav" kit-one-level>
                <li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
                <li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right kit-nav">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> ${user.username }
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">基本资料</a></dd>
                        <dd><a href="javascript:;">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath }/UserServlet?method=logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                 <zhb:permission value="用户管理">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 用户管理</span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'${pageContext.request.contextPath }/UserServlet?method=getAll',icon:'&#xe6c6;',title:'用户列表',id:'1'}">
                                    <i class="layui-icon">&#xe6c6;</i><span> 用户列表</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="../security/adduser.jsp" data-icon="fa-user" data-title="添加用户" kit-target data-id='2'><i class="fa fa-user" aria-hidden="true"></i><span> 添加用户</span></a>
                            </dd>
                           
                        </dl>
                    </li>
                    
                    </zhb:permission>
                    <zhb:permission value="管理权限">
                    <li class="layui-nav-item ">
                        <a href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span> 管理权限</span></a>
                        <dl class="layui-nav-child">
                            <zhb:permission value="权限管理">
                            <dd><a href="javascript:;" kit-target data-options="{url:'${pageContext.request.contextPath }/PrivilegeServlet?method=getAll',icon:'&#xe658;',title:'权限管理',id:'6'}"><i class="layui-icon">&#xe658;</i><span>权限管理</span></a></dd>
                            </zhb:permission>
                    		<zhb:permission value="资源管理">
                            <dd><a href="javascript:;" kit-target data-options="{url:'${pageContext.request.contextPath }/ResourceServlet?method=getAll',icon:'&#xe658;',title:'资源管理',id:'7'}"><i class="layui-icon">&#xe658;</i><span> 资源管理</span></a></dd>
                            </zhb:permission>
                    		<zhb:permission value="角色管理">
                            <dd><a href="javascript:;" kit-target data-options="{url:'${pageContext.request.contextPath }/RoleServlet?method=getAll',icon:'&#xe658;',title:'角色管理',id:'50'}"><i class="layui-icon">&#xe658;</i><span> 角色管理</span></a></dd>
                            </zhb:permission>
                        </dl>
                    </li>
                    </zhb:permission>
                </ul>
            </div>
        </div>
        <div class="layui-body" id="container">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            2017 &copy;
            <a href="http://kit.zhengjinfan.cn/">kit.zhengjinfan.cn/</a> MIT license

        </div>
    </div>
  
    <script src="./plugins/layui/layui.js"></script>
    <script>
        var message;
        layui.config({
            base: 'build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
           
        });
    </script>
</body>

</html>
