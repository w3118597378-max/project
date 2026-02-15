package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BisaijishutongjiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BisaijishutongjiView;


/**
 * 比赛技术统计
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface BisaijishutongjiService extends IService<BisaijishutongjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BisaijishutongjiView> selectListView(Wrapper<BisaijishutongjiEntity> wrapper);
   	
   	BisaijishutongjiView selectView(@Param("ew") Wrapper<BisaijishutongjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BisaijishutongjiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<BisaijishutongjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<BisaijishutongjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<BisaijishutongjiEntity> wrapper);



}

