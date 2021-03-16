package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageable;

public interface JpaRepository<T> {
	List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where);

	List<T> findAll(Map<String, Object> properties, Object... where);

	List<T> findAll(String sql, Pageable pageable, Object... where);
	
	T findById(Long id);

	Long insert(Object object);
}
