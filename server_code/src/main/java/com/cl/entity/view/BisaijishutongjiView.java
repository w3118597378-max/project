package com.cl.entity.view;

import com.cl.entity.BisaijishutongjiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 比赛技术统计
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("bisaijishutongji")
public class BisaijishutongjiView  extends BisaijishutongjiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BisaijishutongjiView(){
	}
 
 	public BisaijishutongjiView(BisaijishutongjiEntity bisaijishutongjiEntity){
 	try {
			BeanUtils.copyProperties(this, bisaijishutongjiEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
