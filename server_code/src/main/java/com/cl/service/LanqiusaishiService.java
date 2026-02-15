package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.LanqiusaishiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LanqiusaishiView;


/**
 * 篮球赛事
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface LanqiusaishiService extends IService<LanqiusaishiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LanqiusaishiView> selectListView(Wrapper<LanqiusaishiEntity> wrapper);
   	
   	LanqiusaishiView selectView(@Param("ew") Wrapper<LanqiusaishiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LanqiusaishiEntity> wrapper);
   	
   
}

