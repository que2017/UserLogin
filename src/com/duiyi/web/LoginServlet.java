package com.duiyi.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.User;
import com.duiyi.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserService service = new UserService();
		// 获取浏览器传过来的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 验证用户名和密码
		User user = service.isUserCorrect(username, password);
		if (user == null) {
			// 用户不存在
			response.getWriter().write("incorrect");
		} else {
			// 用户存在
			request.getSession().setAttribute("user", user);
			// 是否勾选过记住用户名
			if ("ok".equals(request.getParameter("remberUser"))) {
				Cookie cookie = new Cookie("remberUser", URLEncoder.encode(user.getUsername(), "UTF-8"));
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(30 * 24 * 3600);
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("remberUser", "");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			response.getWriter().write("correct");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
