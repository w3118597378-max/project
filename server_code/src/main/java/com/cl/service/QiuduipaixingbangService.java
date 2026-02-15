package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QiuduipaixingbangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QiuduipaixingbangView;


/**
 * 球队排行榜
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface QiuduipaixingbangService extends IService<QiuduipaixingbangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiuduipaixingbangView> selectListView(Wrapper<QiuduipaixingbangEntity> wrapper);
   	
   	QiuduipaixingbangView selectView(@Param("ew") Wrapper<QiuduipaixingbangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiuduipaixingbangEntity> wrapper);
   	
   
}

