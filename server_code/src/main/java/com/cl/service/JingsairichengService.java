package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JingsairichengEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JingsairichengView;


/**
 * 竞赛日程
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JingsairichengService extends IService<JingsairichengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JingsairichengView> selectListView(Wrapper<JingsairichengEntity> wrapper);
   	
   	JingsairichengView selectView(@Param("ew") Wrapper<JingsairichengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JingsairichengEntity> wrapper);
   	
   
}

