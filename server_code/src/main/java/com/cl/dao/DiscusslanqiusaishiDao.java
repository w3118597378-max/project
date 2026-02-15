package com.cl.dao;

import com.cl.entity.DiscusslanqiusaishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusslanqiusaishiView;


/**
 * 篮球赛事评论表
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
public interface DiscusslanqiusaishiDao extends BaseMapper<DiscusslanqiusaishiEntity> {
	
	List<DiscusslanqiusaishiView> selectListView(@Param("ew") Wrapper<DiscusslanqiusaishiEntity> wrapper);

	List<DiscusslanqiusaishiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusslanqiusaishiEntity> wrapper);
	
	DiscusslanqiusaishiView selectView(@Param("ew") Wrapper<DiscusslanqiusaishiEntity> wrapper);


}
