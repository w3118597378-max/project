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


import com.cl.dao.JiaruqiuduiDao;
import com.cl.entity.JiaruqiuduiEntity;
import com.cl.service.JiaruqiuduiService;
import com.cl.entity.view.JiaruqiuduiView;

@Service("jiaruqiuduiService")
public class JiaruqiuduiServiceImpl extends ServiceImpl<JiaruqiuduiDao, JiaruqiuduiEntity> implements JiaruqiuduiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaruqiuduiEntity> page = this.selectPage(
                new Query<JiaruqiuduiEntity>(params).getPage(),
                new EntityWrapper<JiaruqiuduiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiaruqiuduiEntity> wrapper) {
		  Page<JiaruqiuduiView> page =new Query<JiaruqiuduiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JiaruqiuduiView> selectListView(Wrapper<JiaruqiuduiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaruqiuduiView selectView(Wrapper<JiaruqiuduiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
