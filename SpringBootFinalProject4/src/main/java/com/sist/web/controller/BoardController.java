package com.sist.web.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class BoardController {
	
   @Autowired
   private BoardDAO dao;
   
   
   // 목록 
   @GetMapping("/board/list")
   public String board_list(String page,Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   
	   int rowSize=10;
	   int start=(rowSize*curpage)-rowSize;
	   
	   List<BoardEntity> list=dao.boardListData(start);
	   int totalpage=dao.boardTotalPage();
	   
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("list", list);
	   model.addAttribute("main_content", "board/list");
		
	   return "main"; // main에게 데이터 보내기

   }

   
   // 상세
   @GetMapping("/board/detail")
   public String board_detail(int no, Model model)
   {
	   // 조회수 증가 
	   BoardEntity vo=dao.findByNo(no);
	   vo.setHit(vo.getHit()+1);
	   dao.save(vo);
	   
	   vo=dao.findByNo(no);
	   model.addAttribute("vo", vo); // EL => ${} 
	   return "board/detail";
   }
   
   
   // 작성 폼
   @GetMapping("/board/insert")
   public String board_insert()
   {
	   return "board/insert";
   }
   
   
   // 작성 처리
   @PostMapping("/board/insert_ok")
   public String board_insert_ok(BoardEntity vo)
   {
	   dao.save(vo);
	   return "redirect:/board/list";
   }
}