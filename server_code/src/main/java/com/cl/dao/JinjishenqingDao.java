package com.cl.dao;

import com.cl.entity.JinjishenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JinjishenqingView;


/**
 * 晋级申请
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JinjishenqingDao extends BaseMapper<JinjishenqingEntity> {
	
	List<JinjishenqingView> selectListView(@Param("ew") Wrapper<JinjishenqingEntity> wrapper);

	List<JinjishenqingView> selectListView(Pagination page,@Param("ew") Wrapper<JinjishenqingEntity> wrapper);
	
	JinjishenqingView selectView(@Param("ew") Wrapper<JinjishenqingEntity> wrapper);


}
