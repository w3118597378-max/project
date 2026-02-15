package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussqiuduipaixingbangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussqiuduipaixingbangView;


/**
 * 球队排行榜评论表
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
public interface DiscussqiuduipaixingbangService extends IService<DiscussqiuduipaixingbangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussqiuduipaixingbangView> selectListView(Wrapper<DiscussqiuduipaixingbangEntity> wrapper);
   	
   	DiscussqiuduipaixingbangView selectView(@Param("ew") Wrapper<DiscussqiuduipaixingbangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussqiuduipaixingbangEntity> wrapper);
   	
   
}

