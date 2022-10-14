package com.sist.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "movie") // 사용 테이블명
@Getter
@Setter
public class MovieEntity {
	
	@Id
	private int no;
	private int cate;
	private String title, poster, info, director, actors, runtime, company, country, description;
}
