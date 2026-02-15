package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusslanqiusaishiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusslanqiusaishiView;


/**
 * 篮球赛事评论表
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
public interface DiscusslanqiusaishiService extends IService<DiscusslanqiusaishiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusslanqiusaishiView> selectListView(Wrapper<DiscusslanqiusaishiEntity> wrapper);
   	
   	DiscusslanqiusaishiView selectView(@Param("ew") Wrapper<DiscusslanqiusaishiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusslanqiusaishiEntity> wrapper);
   	
   
}

