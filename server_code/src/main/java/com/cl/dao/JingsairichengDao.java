package com.cl.dao;

import com.cl.entity.JingsairichengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JingsairichengView;


/**
 * 竞赛日程
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JingsairichengDao extends BaseMapper<JingsairichengEntity> {
	
	List<JingsairichengView> selectListView(@Param("ew") Wrapper<JingsairichengEntity> wrapper);

	List<JingsairichengView> selectListView(Pagination page,@Param("ew") Wrapper<JingsairichengEntity> wrapper);
	
	JingsairichengView selectView(@Param("ew") Wrapper<JingsairichengEntity> wrapper);


}
