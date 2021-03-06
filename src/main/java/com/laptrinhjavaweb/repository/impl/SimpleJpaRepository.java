package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.JpaRepository;

public class SimpleJpaRepository<T> implements JpaRepository<T> {
	private Class<T> zClass;

	// tu dong lay class tu <T>, Generic JAVA
	@SuppressWarnings("unchecked")
	public SimpleJpaRepository() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder sql = new StringBuilder("select * from " + tableName + " A  where 1=1 ");
		sql = createSQLfindAll(sql, properties);
		if (where != null && where.length > 0) {
			sql.append(where[0]);
		}
		sql.append(" limit " + pageable.getOffset() + "," + pageable.getLimit() + "");
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;
//		PreparedStatement statement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
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
				return new ArrayList<>();
			}
		}
	}

	@Override
	public List<T> findAll(Map<String, Object> properties, Object... where) {
		String tableName = "";
		Map<String, String> mapSql = new HashMap<>();
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder sql = new StringBuilder("select * from " + tableName + " A ");
		// tim kiem du lieu voi staffid
		if (where != null && where.length > 1) {
			mapSql = (Map<String, String>) where[1];

		}
		sql.append(" WHERE 1=1 ");
		sql = createSQLfindAll(sql, properties);
		if (where != null && where.length == 1) {
			sql.append(where[0]);
		}
//		sql.append(" limit "+ pageable.getOffset()+","+pageable.getLimit()+"");
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
//		PreparedStatement statement = null;
		Statement statement = null;

		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
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
				return new ArrayList<>();
			}
		}
	}

	@Override
	public List<T> findAll(String sqlSearch, Pageable pageable, Object... where) {
		StringBuilder sql = new StringBuilder(sqlSearch);
		sql.append(" limit " + pageable.getOffset() + "," + pageable.getLimit() + "");
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
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
				return new ArrayList<>();
			}
		}
	}
	


	protected StringBuilder createSQLfindAll(StringBuilder where, Map<String, Object> params) {
		if (params != null && params.size() > 0) {
			String[] keys = new String[params.size()];
			Object[] values = new Object[params.size()];
			int i = 0;
			for (Map.Entry<String, Object> item : params.entrySet()) {
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;

			}
			for (int i1 = 0; i1 < keys.length; i1++) {
				if ((values[i1] instanceof String) && (StringUtils.isNotBlank(values[i1].toString()))) {
					where.append(" AND LOWER(A." + keys[i1] + ") LIKE '%" + values[i1].toString() + "%' ");
				} else if ((values[i1] instanceof Integer) && (values[i1] != null)) {
					where.append(" AND LOWER(A." + keys[i1] + ") = " + values[i1] + " ");
				} else if ((values[i1] instanceof Long) && (values[i1] != null)) {
					where.append(" AND LOWER(A." + keys[i1] + ") = " + values[i1] + " ");
				}
			}
		}
		return where;
	}

	@Override
	public Long insert(Object object) {
		String sql = createSqlInsert();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//method
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				int index = i+1;
				Field field = fields[i];
				field.setAccessible(true);
				statement.setObject(index, field.get(object));
			}
			
			
			Class<?> parentClass = aClass.getSuperclass();
			int indexParent = fields.length + 1;
			while(parentClass != null) {
				Field[] fieldsParent = parentClass.getDeclaredFields();
				for (int i = 0; i < fieldsParent.length; i++) {
					Field field = fieldsParent[i];
					field.setAccessible(true);
					statement.setObject(indexParent, field.get(object));
					indexParent++;
				}
				//kiem tra xem con extends class nao nua khong
				parentClass = parentClass.getSuperclass();
			}
//			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException | IllegalAccessException e) {
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
		return null;
	}

	private String createSqlInsert() {
		//lay ten bang trong entity
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for(Field field:zClass.getDeclaredFields()) {
			if(fields.length() > 1) {
				fields.append(" , ");
				params.append(" , ");
			}
			if(field.isAnnotationPresent(Column.class)) {
				Column  column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field:parentClass.getDeclaredFields()) {
				if(fields.length() > 1) {
					fields.append(" , ");
					params.append(" , ");
				}
				if(field.isAnnotationPresent(Column.class)) {
					Column  column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		String sql = " INSERT INTO "+tableName+" ( "+fields.toString()+" ) VALUES ( "+params.toString()+" )";
		return sql;
	}

	@Override
	public T findById(Long id) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder sql = new StringBuilder("select * from " + tableName + " A  where id = "+id+" ");
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			List<T> result = resultSetMapper.mapRow(resultSet, this.zClass);
			return result.get(0);
		} catch (SQLException e) {
			return (T) new Object();
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
				return (T) new Object();
			}
		}
	}	
}
