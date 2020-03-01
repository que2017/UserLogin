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
		
		// 1.У����֤��
		String validateStr1 = (String) request.getSession().getAttribute("validateStr");
		String validateStr2 = request.getParameter("validateStr");
		if (validateStr1 == null || validateStr2 == null || !validateStr1.equals(validateStr2)) {
			response.getWriter().write("��֤�벻��ȷ��");
			return;
		}
		
		User user = new User(request.getParameterMap());
		try {
			// 2.����У��
			user.checkValue();
			// 3.����û������ݿ�
			service.registUser(user);
			// 4.��½�û�
			request.getSession().setAttribute("user", user);
			// 5.��ʾ�û�ע��ɹ���3������ҳ
			response.getWriter().write("��ϲ��ע��ɹ�����3������ҳ������");
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
