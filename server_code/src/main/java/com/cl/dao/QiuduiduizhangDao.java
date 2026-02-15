package com.cl.dao;

import com.cl.entity.QiuduiduizhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QiuduiduizhangView;


/**
 * 球队队长
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface QiuduiduizhangDao extends BaseMapper<QiuduiduizhangEntity> {
	
	List<QiuduiduizhangView> selectListView(@Param("ew") Wrapper<QiuduiduizhangEntity> wrapper);

	List<QiuduiduizhangView> selectListView(Pagination page,@Param("ew") Wrapper<QiuduiduizhangEntity> wrapper);
	
	QiuduiduizhangView selectView(@Param("ew") Wrapper<QiuduiduizhangEntity> wrapper);


}
