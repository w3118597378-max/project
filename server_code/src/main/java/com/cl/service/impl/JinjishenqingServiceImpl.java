package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JinjishenqingDao;
import com.cl.entity.JinjishenqingEntity;
import com.cl.service.JinjishenqingService;
import com.cl.entity.view.JinjishenqingView;

@Service("jinjishenqingService")
public class JinjishenqingServiceImpl extends ServiceImpl<JinjishenqingDao, JinjishenqingEntity> implements JinjishenqingService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JinjishenqingEntity> page = this.selectPage(
                new Query<JinjishenqingEntity>(params).getPage(),
                new EntityWrapper<JinjishenqingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JinjishenqingEntity> wrapper) {
		  Page<JinjishenqingView> page =new Query<JinjishenqingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JinjishenqingView> selectListView(Wrapper<JinjishenqingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JinjishenqingView selectView(Wrapper<JinjishenqingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
