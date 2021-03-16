package com.laptrinhjavaweb.service.impl;

import java.util.Date;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IUserSevice;

public class UserService implements IUserSevice{
	private UserConverter userConverter;
	private IUserRepository userRepository;
	
	public UserService() {
		userConverter = new UserConverter();
	}
	@Override
	public UserDTO save(UserDTO userDTO) {
		UserEntity entity = userConverter.convertToEntity(userDTO);
		entity.setCreatedDate(new Date());
		entity.setCreatedBy("a");
		
		return userConverter.convertToDTO(userRepository.save(entity));
	}

}
