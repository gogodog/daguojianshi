package com.dgjs.service.admin;

import java.util.List;

import com.dgjs.model.persistence.Authority;

public interface AuthorityService {

	public int save(Authority authority);
	
	public List<Authority> list();
	
	public int deleteById(Integer id);
	
	public int update(Authority authority);
}
