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


import com.cl.dao.DiscusslanqiusaishiDao;
import com.cl.entity.DiscusslanqiusaishiEntity;
import com.cl.service.DiscusslanqiusaishiService;
import com.cl.entity.view.DiscusslanqiusaishiView;

@Service("discusslanqiusaishiService")
public class DiscusslanqiusaishiServiceImpl extends ServiceImpl<DiscusslanqiusaishiDao, DiscusslanqiusaishiEntity> implements DiscusslanqiusaishiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusslanqiusaishiEntity> page = this.selectPage(
                new Query<DiscusslanqiusaishiEntity>(params).getPage(),
                new EntityWrapper<DiscusslanqiusaishiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusslanqiusaishiEntity> wrapper) {
		  Page<DiscusslanqiusaishiView> page =new Query<DiscusslanqiusaishiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscusslanqiusaishiView> selectListView(Wrapper<DiscusslanqiusaishiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusslanqiusaishiView selectView(Wrapper<DiscusslanqiusaishiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
