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


import com.cl.dao.DiscussqiuduipaixingbangDao;
import com.cl.entity.DiscussqiuduipaixingbangEntity;
import com.cl.service.DiscussqiuduipaixingbangService;
import com.cl.entity.view.DiscussqiuduipaixingbangView;

@Service("discussqiuduipaixingbangService")
public class DiscussqiuduipaixingbangServiceImpl extends ServiceImpl<DiscussqiuduipaixingbangDao, DiscussqiuduipaixingbangEntity> implements DiscussqiuduipaixingbangService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussqiuduipaixingbangEntity> page = this.selectPage(
                new Query<DiscussqiuduipaixingbangEntity>(params).getPage(),
                new EntityWrapper<DiscussqiuduipaixingbangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussqiuduipaixingbangEntity> wrapper) {
		  Page<DiscussqiuduipaixingbangView> page =new Query<DiscussqiuduipaixingbangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscussqiuduipaixingbangView> selectListView(Wrapper<DiscussqiuduipaixingbangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussqiuduipaixingbangView selectView(Wrapper<DiscussqiuduipaixingbangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
