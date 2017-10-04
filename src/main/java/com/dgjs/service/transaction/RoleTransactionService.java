package com.dgjs.service.transaction;


public interface RoleTransactionService {

	public void saveRole(Integer roleId,String roleName,Integer[] authorityIds);
	
}
