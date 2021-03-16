package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;

public class UserConverter {
	public UserDTO convertToDTO(UserEntity entity) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		return dto;
	}

	public UserEntity convertToEntity(UserDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity entity = modelMapper.map(dto, UserEntity.class);
		return entity;
	}
}
