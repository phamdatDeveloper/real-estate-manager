package com.laptrinhjavaweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.DistrictConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = "/admin-building")
public class BuildingController extends HttpServlet {
	private IBuildingService buildingService = new BuildingService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BuildingDTO building = FormUtil.toModel(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url ="";
		if (action != null & action.equals("LIST")) {
			BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder().setName(building.getName()).setDistrict(building.getDistrict())
					.setBuildingArea(building.getBuildingArea()).setNumberOfBasement(building.getNumberOfBasement())
					.setBuildingTypes(building.getBuildingTypes()).setAreaRentFrom(building.getAreaRenFromt()).setAreaRentTo(building.getAreaRentTo())
					.setCostRentFrom(building.getCostRentFrom()).setCostRentTo(building.getCostRentTo())
					.setStaffId(building.getStaffId())
					.build();
			Pageable pageable = new PageRequest(building.getPage(),building.getLimit());
			List<BuildingDTO> buildings = buildingService.findAll(builder,pageable);
			
			request.setAttribute("buildings", buildings);
			
			url ="/views/list.jsp";
		}else if(action != null & action.equals("EDIT")) {
			if(building.getId() != null) {
				//update
			}else {
				buildingService.save(building);
			}
			url ="/views/list.jsp";
		}else {
			url ="/views/home.jsp";
		}
		
		request.setAttribute("districts", buildingService.getDistricts());
		request.setAttribute("buildingTypes", buildingService.getBuildingTypes());
		request.setAttribute("model", building);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
