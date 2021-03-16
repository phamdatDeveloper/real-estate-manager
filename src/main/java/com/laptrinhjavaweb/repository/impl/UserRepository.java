package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends SimpleJpaRepository<UserEntity> implements IUserRepository{

	
	
	@Override
	public UserEntity save(UserEntity userEntity) {
		String sql ="INSERT INTO user(username) values ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			setParameter(statement, parameters);
			statement.setString(1, userEntity.getUserName());
			statement.executeUpdate();
			connection.commit();
			return userEntity;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return new UserEntity();
	}

	
	/*@Override
	public List<UserDTO> findAll() {
		String sql = "";
		List<UserDTO> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
//				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null; 
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}*/



}
