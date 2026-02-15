package com.cl.dao;

import com.cl.entity.JiaruqiuduiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaruqiuduiView;


/**
 * 加入球队
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface JiaruqiuduiDao extends BaseMapper<JiaruqiuduiEntity> {
	
	List<JiaruqiuduiView> selectListView(@Param("ew") Wrapper<JiaruqiuduiEntity> wrapper);

	List<JiaruqiuduiView> selectListView(Pagination page,@Param("ew") Wrapper<JiaruqiuduiEntity> wrapper);
	
	JiaruqiuduiView selectView(@Param("ew") Wrapper<JiaruqiuduiEntity> wrapper);


}
