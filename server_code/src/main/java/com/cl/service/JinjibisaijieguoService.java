package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JinjibisaijieguoEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JinjibisaijieguoView;


/**
 * 晋级比赛结果
 *
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
public interface JinjibisaijieguoService extends IService<JinjibisaijieguoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JinjibisaijieguoView> selectListView(Wrapper<JinjibisaijieguoEntity> wrapper);
   	
   	JinjibisaijieguoView selectView(@Param("ew") Wrapper<JinjibisaijieguoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JinjibisaijieguoEntity> wrapper);
   	
   
}

