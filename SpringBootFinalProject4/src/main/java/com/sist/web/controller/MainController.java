package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class MainController {

	@Autowired
	private MovieDAO dao;
	
	
	// 메인 목록
	@GetMapping("/main")
	public String main_main(String page, int cate, Model model) // model => 전송 객체 => request 대신 처리
	{		
		// 페이징
		if(page == null)	
			page = "1";
		
		
		String cate2 = "";
		
		if(cate == 1)
			cate2 = "현재 상영 중";
		
		if(cate == 2)
			cate2 = "상영 예정";
		
		
		
		
		int curpage = Integer.parseInt(page);
		List<MovieEntity> list = dao.MovieListData((curpage*12)-12, cate);
		int totalpage = dao.MovieTotalPage(cate);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		
		// 제목 길이 자르기
		for(MovieEntity vo:list)
		{
			String s = vo.getTitle();
			if(s.length() > 17)			{
				s = s.substring(0, 17) + "...";
				vo.setTitle(s);
			}
			vo.setTitle(s);
		}
		
		// 배우 길이 자르기
		/*for(LocationEntity vo2:list)
		{
			String s = vo2.getActors();
			if(s.length() > 23)			{
				s = s.substring(0, 23) + "...";
				vo2.setActors(s);
			}
			vo2.setActors(s);
		}*/
		
		// info 길이 자르기
		for(MovieEntity vo3:list)
		{
			String s = vo3.getInfo();
			if(s.length() > 23)			{
				s = s.substring(0, 23) + "...";
				vo3.setInfo(s);
			}
			vo3.setInfo(s);
		}
		
		
		
		model.addAttribute("cate", cate);
		model.addAttribute("cate2", cate2);
		model.addAttribute("main_content", "main/home");
		
		return "main";
	}
	
	
}
