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


import com.cl.dao.JinjibisaijieguoDao;
import com.cl.entity.JinjibisaijieguoEntity;
import com.cl.service.JinjibisaijieguoService;
import com.cl.entity.view.JinjibisaijieguoView;

@Service("jinjibisaijieguoService")
public class JinjibisaijieguoServiceImpl extends ServiceImpl<JinjibisaijieguoDao, JinjibisaijieguoEntity> implements JinjibisaijieguoService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JinjibisaijieguoEntity> page = this.selectPage(
                new Query<JinjibisaijieguoEntity>(params).getPage(),
                new EntityWrapper<JinjibisaijieguoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JinjibisaijieguoEntity> wrapper) {
		  Page<JinjibisaijieguoView> page =new Query<JinjibisaijieguoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JinjibisaijieguoView> selectListView(Wrapper<JinjibisaijieguoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JinjibisaijieguoView selectView(Wrapper<JinjibisaijieguoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
