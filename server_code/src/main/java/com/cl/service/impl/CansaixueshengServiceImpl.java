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


import com.cl.dao.CansaixueshengDao;
import com.cl.entity.CansaixueshengEntity;
import com.cl.service.CansaixueshengService;
import com.cl.entity.view.CansaixueshengView;

@Service("cansaixueshengService")
public class CansaixueshengServiceImpl extends ServiceImpl<CansaixueshengDao, CansaixueshengEntity> implements CansaixueshengService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CansaixueshengEntity> page = this.selectPage(
                new Query<CansaixueshengEntity>(params).getPage(),
                new EntityWrapper<CansaixueshengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CansaixueshengEntity> wrapper) {
		  Page<CansaixueshengView> page =new Query<CansaixueshengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<CansaixueshengView> selectListView(Wrapper<CansaixueshengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CansaixueshengView selectView(Wrapper<CansaixueshengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<CansaixueshengEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<CansaixueshengEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<CansaixueshengEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
