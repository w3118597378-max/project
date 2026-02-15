package com.cl.dao;

import com.cl.entity.QiuduipaixingbangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QiuduipaixingbangView;


/**
 * 球队排行榜
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface QiuduipaixingbangDao extends BaseMapper<QiuduipaixingbangEntity> {
	
	List<QiuduipaixingbangView> selectListView(@Param("ew") Wrapper<QiuduipaixingbangEntity> wrapper);

	List<QiuduipaixingbangView> selectListView(Pagination page,@Param("ew") Wrapper<QiuduipaixingbangEntity> wrapper);
	
	QiuduipaixingbangView selectView(@Param("ew") Wrapper<QiuduipaixingbangEntity> wrapper);


}
