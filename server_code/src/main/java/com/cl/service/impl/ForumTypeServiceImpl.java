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


import com.cl.dao.ForumTypeDao;
import com.cl.entity.ForumTypeEntity;
import com.cl.service.ForumTypeService;
import com.cl.entity.view.ForumTypeView;

@Service("forumTypeService")
public class ForumTypeServiceImpl extends ServiceImpl<ForumTypeDao, ForumTypeEntity> implements ForumTypeService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumTypeEntity> page = this.selectPage(
                new Query<ForumTypeEntity>(params).getPage(),
                new EntityWrapper<ForumTypeEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumTypeEntity> wrapper) {
		  Page<ForumTypeView> page =new Query<ForumTypeView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ForumTypeView> selectListView(Wrapper<ForumTypeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumTypeView selectView(Wrapper<ForumTypeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
