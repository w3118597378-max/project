package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JinjishenqingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JinjishenqingView;


/**
 * 晋级申请
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JinjishenqingService extends IService<JinjishenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JinjishenqingView> selectListView(Wrapper<JinjishenqingEntity> wrapper);
   	
   	JinjishenqingView selectView(@Param("ew") Wrapper<JinjishenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JinjishenqingEntity> wrapper);
   	
   
}

