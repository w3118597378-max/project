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


import com.cl.dao.SensitiveWordDao;
import com.cl.entity.SensitiveWordEntity;
import com.cl.service.SensitiveWordService;
import com.cl.entity.view.SensitiveWordView;

@Service("sensitiveWordService")
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordDao, SensitiveWordEntity> implements SensitiveWordService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SensitiveWordEntity> page = this.selectPage(
                new Query<SensitiveWordEntity>(params).getPage(),
                new EntityWrapper<SensitiveWordEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<SensitiveWordEntity> wrapper) {
		  Page<SensitiveWordView> page =new Query<SensitiveWordView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<SensitiveWordView> selectListView(Wrapper<SensitiveWordEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public SensitiveWordView selectView(Wrapper<SensitiveWordEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
