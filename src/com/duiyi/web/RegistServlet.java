package com.duiyi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.User;
import com.duiyi.exception.MsgException;
import com.duiyi.service.UserService;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserService service = new UserService();
		
		// 1.校验验证码
		String validateStr1 = (String) request.getSession().getAttribute("validateStr");
		String validateStr2 = request.getParameter("validateStr");
		if (validateStr1 == null || validateStr2 == null || !validateStr1.equals(validateStr2)) {
			response.getWriter().write("验证码不正确！");
			return;
		}
		
		User user = new User(request.getParameterMap());
		try {
			// 2.数据校验
			user.checkValue();
			// 3.添加用户到数据库
			service.registUser(user);
			// 4.登陆用户
			request.getSession().setAttribute("user", user);
			// 5.提示用户注册成功，3秒后回主页
			response.getWriter().write("恭喜您注册成功！！3秒后回主页。。。");
			response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.html");
		} catch (MsgException e) {
			response.getWriter().write(e.getMessage().toString());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
