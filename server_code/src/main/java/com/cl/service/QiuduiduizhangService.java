package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QiuduiduizhangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QiuduiduizhangView;


/**
 * 球队队长
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface QiuduiduizhangService extends IService<QiuduiduizhangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiuduiduizhangView> selectListView(Wrapper<QiuduiduizhangEntity> wrapper);
   	
   	QiuduiduizhangView selectView(@Param("ew") Wrapper<QiuduiduizhangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiuduiduizhangEntity> wrapper);
   	
   
}

