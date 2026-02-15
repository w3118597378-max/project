package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.CansaixueshengEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.CansaixueshengView;


/**
 * 参赛学生
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface CansaixueshengService extends IService<CansaixueshengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CansaixueshengView> selectListView(Wrapper<CansaixueshengEntity> wrapper);
   	
   	CansaixueshengView selectView(@Param("ew") Wrapper<CansaixueshengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CansaixueshengEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<CansaixueshengEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<CansaixueshengEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<CansaixueshengEntity> wrapper);



}

