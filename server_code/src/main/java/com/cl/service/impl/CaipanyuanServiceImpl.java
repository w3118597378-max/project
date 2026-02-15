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


import com.cl.dao.CaipanyuanDao;
import com.cl.entity.CaipanyuanEntity;
import com.cl.service.CaipanyuanService;
import com.cl.entity.view.CaipanyuanView;

@Service("caipanyuanService")
public class CaipanyuanServiceImpl extends ServiceImpl<CaipanyuanDao, CaipanyuanEntity> implements CaipanyuanService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaipanyuanEntity> page = this.selectPage(
                new Query<CaipanyuanEntity>(params).getPage(),
                new EntityWrapper<CaipanyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaipanyuanEntity> wrapper) {
		  Page<CaipanyuanView> page =new Query<CaipanyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<CaipanyuanView> selectListView(Wrapper<CaipanyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CaipanyuanView selectView(Wrapper<CaipanyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
