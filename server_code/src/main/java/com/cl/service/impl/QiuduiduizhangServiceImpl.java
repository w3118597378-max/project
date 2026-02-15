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


import com.cl.dao.QiuduiduizhangDao;
import com.cl.entity.QiuduiduizhangEntity;
import com.cl.service.QiuduiduizhangService;
import com.cl.entity.view.QiuduiduizhangView;

@Service("qiuduiduizhangService")
public class QiuduiduizhangServiceImpl extends ServiceImpl<QiuduiduizhangDao, QiuduiduizhangEntity> implements QiuduiduizhangService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiuduiduizhangEntity> page = this.selectPage(
                new Query<QiuduiduizhangEntity>(params).getPage(),
                new EntityWrapper<QiuduiduizhangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiuduiduizhangEntity> wrapper) {
		  Page<QiuduiduizhangView> page =new Query<QiuduiduizhangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<QiuduiduizhangView> selectListView(Wrapper<QiuduiduizhangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiuduiduizhangView selectView(Wrapper<QiuduiduizhangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
