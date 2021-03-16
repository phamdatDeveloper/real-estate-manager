package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity>{
//	List<UserDTO> findAll();
	UserEntity save(UserEntity userEntity);
}
