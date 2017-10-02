package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.Authority;

public interface AuthorityMapper {

	public int save(Authority authority);
	
	public Authority selectById(Integer id);
	
	public int update(Authority authority);
	
	public List<Authority> list();
	
	public int deleteById(Integer id);
}
