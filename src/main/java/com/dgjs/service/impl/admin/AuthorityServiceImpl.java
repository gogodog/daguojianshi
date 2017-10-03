package com.dgjs.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AuthorityMapper;
import com.dgjs.model.persistence.Authority;
import com.dgjs.service.admin.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityMapper authorityMapper;
	
	@Override
	public int save(Authority authority) {
		return authorityMapper.save(authority);
	}

	@Override
	public List<Authority> list() {
		return authorityMapper.list();
	}

	@Override
	public int deleteById(Integer id) {
		return authorityMapper.deleteById(id);
	}

	@Override
	public int update(Authority authority) {
		return authorityMapper.update(authority);
	}

}
