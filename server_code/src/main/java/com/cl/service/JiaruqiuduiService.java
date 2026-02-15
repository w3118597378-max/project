package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiaruqiuduiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaruqiuduiView;


/**
 * 加入球队
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
public interface JiaruqiuduiService extends IService<JiaruqiuduiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaruqiuduiView> selectListView(Wrapper<JiaruqiuduiEntity> wrapper);
   	
   	JiaruqiuduiView selectView(@Param("ew") Wrapper<JiaruqiuduiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiaruqiuduiEntity> wrapper);
   	
   
}

