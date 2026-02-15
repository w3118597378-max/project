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


import com.cl.dao.ForumReportDao;
import com.cl.entity.ForumReportEntity;
import com.cl.service.ForumReportService;
import com.cl.entity.view.ForumReportView;

@Service("forumReportService")
public class ForumReportServiceImpl extends ServiceImpl<ForumReportDao, ForumReportEntity> implements ForumReportService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumReportEntity> page = this.selectPage(
                new Query<ForumReportEntity>(params).getPage(),
                new EntityWrapper<ForumReportEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumReportEntity> wrapper) {
		  Page<ForumReportView> page =new Query<ForumReportView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ForumReportView> selectListView(Wrapper<ForumReportEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumReportView selectView(Wrapper<ForumReportEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
