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


import com.cl.dao.JingsairichengDao;
import com.cl.entity.JingsairichengEntity;
import com.cl.service.JingsairichengService;
import com.cl.entity.view.JingsairichengView;

@Service("jingsairichengService")
public class JingsairichengServiceImpl extends ServiceImpl<JingsairichengDao, JingsairichengEntity> implements JingsairichengService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JingsairichengEntity> page = this.selectPage(
                new Query<JingsairichengEntity>(params).getPage(),
                new EntityWrapper<JingsairichengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JingsairichengEntity> wrapper) {
		  Page<JingsairichengView> page =new Query<JingsairichengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JingsairichengView> selectListView(Wrapper<JingsairichengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JingsairichengView selectView(Wrapper<JingsairichengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
