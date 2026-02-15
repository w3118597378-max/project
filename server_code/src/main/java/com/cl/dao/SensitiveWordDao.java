package com.cl.dao;

import com.cl.entity.SensitiveWordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.SensitiveWordView;


/**
 * 敏感词
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface SensitiveWordDao extends BaseMapper<SensitiveWordEntity> {
	
	List<SensitiveWordView> selectListView(@Param("ew") Wrapper<SensitiveWordEntity> wrapper);

	List<SensitiveWordView> selectListView(Pagination page,@Param("ew") Wrapper<SensitiveWordEntity> wrapper);
	
	SensitiveWordView selectView(@Param("ew") Wrapper<SensitiveWordEntity> wrapper);


}
