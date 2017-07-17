package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.ChannelArticlescrapDto;
import com.dgjs.model.persistence.Channel;
import com.dgjs.model.persistence.ChannelArticlescrap;

public interface ChannelService {

    public int save(Channel channel);
    
    public int update(Channel channel);
    
    public List<Channel> list();    
    
    public int deleteById(Integer id);
    
    public int saveCA(ChannelArticlescrap ca);
    
    public int updateCA(String id,int sort);
    
    public int deleteCA(String id);
    
    public List<ChannelArticlescrapDto> listCA(Integer channelId);
    
    public Channel selectById(Integer id);
}
