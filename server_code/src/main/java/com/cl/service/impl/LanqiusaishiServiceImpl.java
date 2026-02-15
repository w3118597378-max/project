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


import com.cl.dao.LanqiusaishiDao;
import com.cl.entity.LanqiusaishiEntity;
import com.cl.service.LanqiusaishiService;
import com.cl.entity.view.LanqiusaishiView;

@Service("lanqiusaishiService")
public class LanqiusaishiServiceImpl extends ServiceImpl<LanqiusaishiDao, LanqiusaishiEntity> implements LanqiusaishiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<LanqiusaishiEntity> page = this.selectPage(
                new Query<LanqiusaishiEntity>(params).getPage(),
                new EntityWrapper<LanqiusaishiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<LanqiusaishiEntity> wrapper) {
		  Page<LanqiusaishiView> page =new Query<LanqiusaishiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<LanqiusaishiView> selectListView(Wrapper<LanqiusaishiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public LanqiusaishiView selectView(Wrapper<LanqiusaishiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
