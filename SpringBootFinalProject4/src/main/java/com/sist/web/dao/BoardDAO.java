package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sist.web.entity.BoardEntity;
import java.util.*;

@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{
    
	// 상세
	public BoardEntity findByNo(@Param("no") Integer no);
     
	
	// 목록
    @Query(value="SELECT no, name, subject, content, hit, regdate, pwd "
    	   +"FROM board ORDER BY no DESC LIMIT :start,10", nativeQuery = true)
    public List<BoardEntity> boardListData(@Param("start") Integer start);
    
    
    // 전체 페이지
    @Query(value="SELECT CEIL(COUNT(*)/10.0) FROM board")
    public int boardTotalPage();

    
}