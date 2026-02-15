package com.cl.dao;

import com.cl.entity.JinjibisaijieguoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JinjibisaijieguoView;


/**
 * 晋级比赛结果
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JinjibisaijieguoDao extends BaseMapper<JinjibisaijieguoEntity> {
	
	List<JinjibisaijieguoView> selectListView(@Param("ew") Wrapper<JinjibisaijieguoEntity> wrapper);

	List<JinjibisaijieguoView> selectListView(Pagination page,@Param("ew") Wrapper<JinjibisaijieguoEntity> wrapper);
	
	JinjibisaijieguoView selectView(@Param("ew") Wrapper<JinjibisaijieguoEntity> wrapper);


}
