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


import com.cl.dao.QiuduipaixingbangDao;
import com.cl.entity.QiuduipaixingbangEntity;
import com.cl.service.QiuduipaixingbangService;
import com.cl.entity.view.QiuduipaixingbangView;

@Service("qiuduipaixingbangService")
public class QiuduipaixingbangServiceImpl extends ServiceImpl<QiuduipaixingbangDao, QiuduipaixingbangEntity> implements QiuduipaixingbangService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiuduipaixingbangEntity> page = this.selectPage(
                new Query<QiuduipaixingbangEntity>(params).getPage(),
                new EntityWrapper<QiuduipaixingbangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiuduipaixingbangEntity> wrapper) {
		  Page<QiuduipaixingbangView> page =new Query<QiuduipaixingbangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<QiuduipaixingbangView> selectListView(Wrapper<QiuduipaixingbangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiuduipaixingbangView selectView(Wrapper<QiuduipaixingbangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
