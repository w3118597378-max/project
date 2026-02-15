package com.cl.dao;

import com.cl.entity.LanqiusaishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LanqiusaishiView;


/**
 * 篮球赛事
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface LanqiusaishiDao extends BaseMapper<LanqiusaishiEntity> {
	
	List<LanqiusaishiView> selectListView(@Param("ew") Wrapper<LanqiusaishiEntity> wrapper);

	List<LanqiusaishiView> selectListView(Pagination page,@Param("ew") Wrapper<LanqiusaishiEntity> wrapper);
	
	LanqiusaishiView selectView(@Param("ew") Wrapper<LanqiusaishiEntity> wrapper);


}
