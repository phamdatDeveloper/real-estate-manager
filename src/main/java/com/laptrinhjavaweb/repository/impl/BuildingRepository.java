package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends SimpleJpaRepository<BuildingEntity> implements IBuildingRepository{

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params,Pageable pageable,BuildingSearchBuilder fieldSearch) {
		
		StringBuilder sqlSearch = new  StringBuilder(" SELECT * FROM building A ");
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())) {
			sqlSearch.append(" INNER JOIN assignmentstaff assignstaff ON A.id = assignstaff.buildingid ");
		}
		sqlSearch.append(" WHERE 1=1 ");
		sqlSearch= this.createSQLfindAll(sqlSearch, params);
		String sqlSpecial = buildSqlSpecial(fieldSearch);
		sqlSearch.append(sqlSpecial);
		return this.findAll(sqlSearch.toString(), pageable);
		
	}
	private String buildSqlSpecial(BuildingSearchBuilder fieldSearch) {
		StringBuilder result = new StringBuilder("");
		if(StringUtils.isNotBlank(fieldSearch.getCostRentFrom())) {
			result.append(" AND A.costrent >= "+ fieldSearch.getCostRentFrom()+ " ");
		}
		if (StringUtils.isNotBlank(fieldSearch.getCostRentTo())) {
			result.append(" AND A.costrent <= "+ fieldSearch.getCostRentTo()+" ");
		}
		if(fieldSearch.getBuildingTypes().length > 0 && StringUtils.isNotBlank(fieldSearch.getBuildingTypes()[0])) {
			result.append(" AND (");
			//java 7
			/*int i = 0;
			for (String item : fieldSearch.getBuildingTypes()) {
				if(i == 0) {
					result.append("A.type like '%"+item+"%' ");
				}else {
					result.append(" OR A.type like '%"+item+"%' ");
				}
				i++;
			}
			result.append(")");*/
			
			//java 8
			result.append("A.type like '%"+fieldSearch.getBuildingTypes()[0]+"%' ");
			//Stream trong array
//			Arrays.stream(fieldSearch.getBuildingTypes()).filter(item -> !item.equals(fieldSearch.getBuildingTypes()[0])).forEach(item -> result.append(" OR A.type like '%"+item+"%' "));
			String buildingTypeSql = Arrays.stream(fieldSearch.getBuildingTypes()).map(item -> " A.type LIKE '%"+item+"%' ")
																	.collect(Collectors.joining(" OR "));
			result.append(buildingTypeSql+ ")");
		}
		if(StringUtils.isNotBlank(fieldSearch.getAreaRentFrom()) || StringUtils.isNotBlank(fieldSearch.getAreaRentTo())) {
			result.append(" AND EXISTS (SELECT * FROM renarea ra WHERE (ra.buildingid = A.id");
			if(fieldSearch.getAreaRentFrom() != null) {
				result.append(" AND ra.value >=" +fieldSearch.getAreaRentFrom()+" ");
			}
			if(fieldSearch.getAreaRentTo() != null) {
				result.append(" AND ra.value <=" +fieldSearch.getAreaRentTo()+" ");
			}
			result.append("))");
		}
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())) {
			result.append(" AND assignstaff.staffid = "+fieldSearch.getStaffId()+" ");
		}
		return result.toString();
	}
	@Override
	public BuildingEntity save(BuildingEntity buildingEntity) {
		String sql ="INSERT INTO BUILDING(";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();
			return buildingEntity;
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
		return new BuildingEntity();
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
//		String name = (String) params.get("name");
//		String district = (String) params.get("district");
//		StringBuilder where =new StringBuilder("");
		//student code
		/*StringBuilder where =new StringBuilder("");
		if(StringUtils.isNotBlank(name)) {
			where.append(" AND A.name LIKE '%"+name+"%' ");
		}
		if(StringUtils.isNotBlank(district)) {
			where.append(" AND A.district LIKE '%"+district+"%' ");
		}
		if(params.get("buildingarea") != null) {
			Integer buildingArea = (int) params.get("buildingarea");
			where.append(" AND A.buildingarea = "+buildingArea+" ");
		}
		if(params.get("numberofbasement") != null) {
			Integer numberOfBasement = (int) params.get("numberofbasement");
			where.append(" AND A.numberofbasement ="+numberOfBasement+" ");
		}*/
		
		// 2 nam kinh nghiem
	/*	if(params != null && params.size() >0) {
			String[] keys = new String [params.size()];
			Object[] values = new Object [params.size()];
			int i = 0;
			for(Map.Entry<String, Object> item : params.entrySet()) {
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;
				
			}
			for (int i1 = 0; i1 < keys.length; i1++) {
				if((values[i1] instanceof String) && (StringUtils.isNotBlank(values[i1].toString()))) {
					where.append(" AND LOWER(A."+keys[i1]+") LIKE '%"+values[i1].toString()+"%' ");
				}else if((values[i1] instanceof Integer) && (values[i1] != null)) {
					where.append(" AND LOWER(A."+keys[i1]+") = "+values[i1]+" ");
				}else if((values[i1] instanceof Long) && (values[i1] != null)) {
					where.append(" AND LOWER(A."+keys[i1]+") = "+values[i1]+" "); 
				}
			}
		}
		
		
		return this.findAll(pageable,where.toString());
	}*/

	


