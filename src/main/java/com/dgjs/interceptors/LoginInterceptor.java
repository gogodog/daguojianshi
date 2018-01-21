package com.dgjs.interceptors;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Session_Keys;
import com.dgjs.exceptions.AuthorityException;
import com.dgjs.model.dto.RoleAuthorityDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.Authority;
import com.dgjs.model.result.view.AdminMenu;
import com.dgjs.model.result.view.AdminMenu.Children;
import com.dgjs.model.result.view.CpsMenu;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.RoleService;
import com.dgjs.utils.StringUtils;
import com.dgjs.utils.WebContextHelper;

public class LoginInterceptor implements HandlerInterceptor{
	
	/*
	 * 所有admin菜单信息
	 */
	private static List<AdminMenu> adminMenuList;
	
	/*
	 * 所有cps菜单信息
	 */
	private static List<CpsMenu> cpsMenuList;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AdminUserService adminUserService;

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//初始化后台菜单（目前admin，cps）
		initMenu();
		//设置登录用户信息
		AdminUser adminUser = null;
		String usercode=request.getParameter("usercode");
		if(StringUtils.isNullOrEmpty(usercode)){
			//设置登录用户信息
		    adminUser = WebContextHelper.getAdminUser();
		    if(adminUser == null){
				throw new AuthorityException();
			}
		    //设置用户ehcache缓存，目的是用户改了角色可在一分钟内同步权限信息
		    AdminUser adminUserCache = adminUserService.getByUserCode(adminUser.getUser_code());
		    adminUser = adminUserCache;
		}else{
			adminUser = adminUserService.getByUserCode(usercode);
			if(adminUser == null){
				throw new AuthorityException();
			}
		}
		request.getSession().setAttribute(Session_Keys.USER_INFO, adminUser);
		//获取权限
		RoleAuthorityDto dto = roleService.selectByIdMCache(adminUser.getRole_id());
		List<Authority> authorityList = dto.getAuthoritys();
		List<String> authorityUrlList = new ArrayList<String>(authorityList.size());
		for(Authority authority:authorityList){
			authorityUrlList.add(authority.getAuthority_url());
		}
		//根据权限配置判断admin菜单展示
		List<AdminMenu> showMenus = new ArrayList<AdminMenu>();
		for(AdminMenu adminMenu:adminMenuList){
			List<Children> childrenList = adminMenu.getChildren();
			AdminMenu userMenu = null;
			for(Children children:childrenList){
				//显示菜单逻辑
				if(isPattern(authorityUrlList,children.getUrl())){
					if(userMenu==null){
						userMenu = new AdminMenu();
						userMenu.setName(adminMenu.getName());
						List<Children> userChildrenList = new ArrayList<Children>();
						userChildrenList.add(children);
						userMenu.setChildren(userChildrenList);
					}else{
						List<Children> userChildrenList = userMenu.getChildren();
						userChildrenList.add(children);
					}
				}
			}
			if(userMenu!=null){
				showMenus.add(userMenu);
			}
		}
		//根据权限配置判断cps菜单展示
		List<CpsMenu> cpsShowMenus = new ArrayList<CpsMenu>();
		for(CpsMenu cpsMenu:cpsMenuList){
			if(isPattern(authorityUrlList,cpsMenu.getUrl())){
				cpsShowMenus.add(cpsMenu);
			}
		}
		request.setAttribute("user", adminUser);
		request.setAttribute("menus", showMenus);
		request.setAttribute("cpsmenus", cpsShowMenus);
		//权限判断
		String servletPath = request.getServletPath();
		if(servletPath.startsWith("/admin")||servletPath.startsWith("/cps")){
			if(!isPattern(authorityUrlList,servletPath)){
				throw new AuthorityException();
			}
		}
		return true;
	}

	private boolean isPattern(List<String> regexs,String url){
		if(regexs == null || regexs.isEmpty()){
			return true;
		}
		for(String regex:regexs){
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			if(matcher.matches()){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void initMenu() throws DocumentException{
		if(adminMenuList == null|| cpsMenuList == null){
			SAXReader reader = new SAXReader(); // 解析的xml文档
			InputStream is = LoginInterceptor.class.getClassLoader().getResourceAsStream("menu.xml");
			Document doc = reader.read(is);
			Element root = doc.getRootElement(); // 获取根节点
			Iterator<Element> it = root.elementIterator("project");
			while (it.hasNext()) {
				Element project = it.next();
				String name = project.attribute("id").getText();
				if("admin".equals(name)){
					if(adminMenuList==null){
						adminMenuList = new ArrayList<AdminMenu>();
						Iterator<Element> firstlevelIt = project.elementIterator("firstlevel");
						while(firstlevelIt.hasNext()){
							Element firstlevel = firstlevelIt.next();
							String desc = firstlevel.attribute("desc").getText();
							List<Children> childrenList = new ArrayList<Children>();
							AdminMenu adminMenu = new AdminMenu();
							adminMenu.setName(desc);
							adminMenu.setChildren(childrenList);
							adminMenuList.add(adminMenu);
							Iterator<Element> secondlevelIt=firstlevel.elementIterator("secondlevel");
							while(secondlevelIt.hasNext()){
								Element secondlevel = secondlevelIt.next();
								AdminMenu.Children children =  adminMenu.new Children();
								children.setUrl(secondlevel.attribute("url").getText());
								children.setDesc(secondlevel.attribute("desc").getText());
								childrenList.add(children);
							}
						}
					}
				}else if("cps".equals(name)){
					if(cpsMenuList == null){
						cpsMenuList = new ArrayList<CpsMenu>();
						Iterator<Element> firstlevelIt = project.elementIterator("firstlevel");
						while(firstlevelIt.hasNext()){
							Element firstlevel = firstlevelIt.next();
							String desc = firstlevel.attribute("desc").getText();
							String url = firstlevel.attribute("url").getText();
							String css = firstlevel.attribute("css").getText();
							String pageName = firstlevel.attribute("page_name").getText();
							CpsMenu cpsMenu = new CpsMenu();
							cpsMenu.setCss(css);
							cpsMenu.setName(desc);
							cpsMenu.setUrl(url);
                            if(pageName!=null){
                            	String[] pageNames = pageName.split(",");
                            	cpsMenu.setPageName(pageNames);
							}
                            cpsMenuList.add(cpsMenu);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		LoginInterceptor l = new LoginInterceptor();
		//全部 .*+
//		boolean flag = l.isPattern(Arrays.asList("/cps/.*/.*"), "/cps/dft/draft");
		boolean flag = l.isPattern(Arrays.asList("/cps/ck|/cps/log.*"), "/cps/ligout");
		System.out.println(flag);
	}
}

