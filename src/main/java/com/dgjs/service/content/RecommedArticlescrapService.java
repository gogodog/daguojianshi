package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;

public interface RecommedArticlescrapService {

     public int save(String articlescrapId,UpDown_Status status,int sort) throws Exception;
	 
	 public List<Articlescrap> list(UpDown_Status status);

     public int deleteById(String articlescrapId) throws Exception;
    
     public int updateStatus(String id,UpDown_Status status);
}
