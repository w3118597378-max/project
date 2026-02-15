package com.cl.dao;

import com.cl.entity.DiscussqiuduipaixingbangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussqiuduipaixingbangView;


/**
 * 球队排行榜评论表
 * 
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
public interface DiscussqiuduipaixingbangDao extends BaseMapper<DiscussqiuduipaixingbangEntity> {
	
	List<DiscussqiuduipaixingbangView> selectListView(@Param("ew") Wrapper<DiscussqiuduipaixingbangEntity> wrapper);

	List<DiscussqiuduipaixingbangView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussqiuduipaixingbangEntity> wrapper);
	
	DiscussqiuduipaixingbangView selectView(@Param("ew") Wrapper<DiscussqiuduipaixingbangEntity> wrapper);


}
