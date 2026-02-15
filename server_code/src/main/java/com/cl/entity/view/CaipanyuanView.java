package com.cl.entity.view;

import com.cl.entity.CaipanyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 裁判员
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@TableName("caipanyuan")
public class CaipanyuanView  extends CaipanyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CaipanyuanView(){
	}
 
 	public CaipanyuanView(CaipanyuanEntity caipanyuanEntity){
 	try {
			BeanUtils.copyProperties(this, caipanyuanEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
