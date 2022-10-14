package com.sist.web.dao;

import java.util.*;
import com.sist.web.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Integer>{

	// 목록 
	@Query(value = "SELECT * FROM movie WHERE cate=:cate "
			+ "ORDER BY no ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
					// 페이지 나누기
	public List<MovieEntity> MovieListData(@Param("start") Integer start, @Param("cate") Integer cate);
	

	
	
	// 전체 페이지 
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM movie WHERE cate=:cate", nativeQuery = true)
	public int MovieTotalPage(@Param("cate") Integer cate);
	
	
	
	
	// 상세 => no로 찾기
	public MovieEntity findByNo(int no);
	
;}
