package com.sist.web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import com.sist.web.manager.*;


@Controller
public class MovieController {

	@Autowired
	private MovieDAO dao;
	
	@Autowired
	private NewsManager mgr;
	
	
	
	
	// 상세
	@GetMapping("/movie/detail")
	public String seoul_detail(int no, Model model) // model=>데이터보내기
	{
		MovieEntity vo = dao.findByNo(no);

		model.addAttribute("vo", vo);
		model.addAttribute("main_content", "movie/detail");
		
		return "main"; // main에게 데이터 보내기
	}
	
	
	// 뉴스
	@RequestMapping("/movie/news")
	public String seoul_news(String ss, Model model)
	{
		if(ss == null)
			ss = "상품";
		
		String json = mgr.newsFind(ss);
		List<NewsVO> list = new ArrayList<NewsVO>();
		try
		{
			JSONParser jp = new JSONParser(); // { items: [{title: description}, {}, {} ..]}
			JSONObject root = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)root.get("items");
			for(int i=0; i<arr.size(); i++)
			{
				JSONObject obj = (JSONObject)arr.get(i);
				NewsVO vo = new NewsVO();
				
				// 한글 제외하고 나머지 공백->제목
				String title = (String)obj.get("title");
				title = title.replaceAll("[^가-힣]", " ");
				vo.setTitle(title);
				
				// 한글 제외하고 나머지 공백->내용 
				String desc = (String)obj.get("description");
				desc = desc.replaceAll("[^가-힣]", " ");
				vo.setDescription(desc);
				
				vo.setLink((String)obj.get("link"));
				
				list.add(vo); // list 변수에 vo 값 담아 줌
			}
		
		}catch(Exception ex) {}
		
		model.addAttribute("list", list);
		model.addAttribute("main_content", "movie/news");
		
		return "main";
	}
	
}
