package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.api.output.StaffOutput;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserSevice;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = "/api-user")
public class UserApi extends HttpServlet {
	private IUserSevice userSerSevice;
	public UserApi() {
		userSerSevice = new UserService();
	}

	private static final long serialVersionUID = 7868130991740727082L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String type = request.getParameter("type");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		if(type != null && type.equals("SHOW_STAFF_ASSIGMENT")) {
			List<StaffOutput> staffs = new ArrayList<StaffOutput>();
			mapper.writeValue(response.getOutputStream(), staffs); 
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		userDTO = userSerSevice.save(userDTO);
		mapper.writeValue(response.getOutputStream(), userDTO); 
	}

}
