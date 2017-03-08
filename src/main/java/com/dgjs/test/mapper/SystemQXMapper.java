package com.dgjs.test.mapper;

import com.dgjs.test.model.SystemQX;
import java.util.List;
import java.util.Map;


public interface SystemQXMapper{
    List<Map<String,Object>> selectlikename(SystemQX qx);
}