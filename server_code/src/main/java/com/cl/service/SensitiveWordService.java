package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.SensitiveWordEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.SensitiveWordView;


/**
 * 敏感词
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface SensitiveWordService extends IService<SensitiveWordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<SensitiveWordView> selectListView(Wrapper<SensitiveWordEntity> wrapper);
   	
   	SensitiveWordView selectView(@Param("ew") Wrapper<SensitiveWordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<SensitiveWordEntity> wrapper);
   	
   
}

